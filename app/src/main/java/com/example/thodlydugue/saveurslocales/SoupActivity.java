package com.example.thodlydugue.saveurslocales;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.thodlydugue.saveurslocales.Adapter.ListviewrecetteAdapter;
import com.example.thodlydugue.saveurslocales.Modele.recettes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SoupActivity extends AppCompatActivity {

    ProgressDialog progress;
    public static final String AplicationID = "0194C491-E19B-FE7A-FFBD-B3BBAC63CE00";
    public static final String SecretKey = "006C1FAB-E51B-1553-FF78-0252E07EA900";

    public ArrayList<recettes> listRecette;
    public ListView lvrecette;
    public ListviewrecetteAdapter adapterRecette;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette);

        //Setup Toolbar,and back button
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_backbutton1);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("Soupes");
        Backendless.initApp(getApplicationContext(), AplicationID, SecretKey);


        lvrecette = (ListView) findViewById(R.id.lvrecette);
        listRecette = new ArrayList<>();
        adapterRecette = new ListviewrecetteAdapter(this, listRecette);

        lvrecette.setAdapter(adapterRecette);

        //StringBuilder whereClause = new StringBuilder();
        //whereClause.append( "categories[nom_categorie]" );
        //whereClause.append( ".objectId='" ).append( "1D075E85-8468-6319-FF44-4831E11AB400"  );


        //  String whereClause = "categorie=Viandes";
        IDataStore<Map> personneStorage = Backendless.Data.of("recettes");


        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        // queryBuilder.setWhereClause( whereClause.toString());

        queryBuilder.setWhereClause("id_categorie='C39E236A-9ECC-24A1-FFBE-E9DD33731C00'");
        setloading();
        personneStorage.find(queryBuilder, new AsyncCallback<List<Map>>()

        {

            @Override
            public void handleResponse(List<Map> response) {

                //recette = (recettes.fromListMap(response));
                adapterRecette.addAll(recettes.fromListMap(response));
                progress.dismiss();
                adapterRecette.notifyDataSetChanged();
                Log.d("DEBUG", lvrecette.toString());
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(getApplicationContext(), fault.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        lvrecette.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                recettes recette = listRecette.get(position);

                Intent intent = new Intent(SoupActivity.this, DetailsActivity.class);
                intent.putExtra("recettes", recette);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_search; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //// Expand the search view and request focus
        //searchItem.expandActionView();
        //searchView.requestFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here

                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                fetchsoup(query);
                // Reset SearchView
                searchView.clearFocus();
                searchView.setQuery("", false);
                searchView.setIconified(true);
                searchItem.collapseActionView();
                // Set activity title to search query
                SoupActivity.this.setTitle(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;


    }


    private void fetchsoup(String query) {
        lvrecette = (ListView) findViewById(R.id.lvrecette);
        listRecette = new ArrayList<>();
        adapterRecette = new ListviewrecetteAdapter(this, listRecette);

        lvrecette.setAdapter(adapterRecette);


        //StringBuilder whereClause = new StringBuilder();
        //whereClause.append( "categories[nom_categorie]" );
        //whereClause.append( ".objectId='" ).append( "1D075E85-8468-6319-FF44-4831E11AB400"  );


        //  String whereClause = "categorie=Viandes";
        IDataStore<Map> recettesStorage = Backendless.Data.of("recettes");


        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        // queryBuilder.setWhereClause( whereClause.toString());

        queryBuilder.setWhereClause("nom_recette like'%" + query + "%'");

        recettesStorage.find(queryBuilder, new AsyncCallback<List<Map>>()

        {


            @Override
            public void handleResponse(List<Map> response) {

                //recette = (recettes.fromListMap(response));
                adapterRecette.addAll(recettes.fromListMap(response));
                adapterRecette.notifyDataSetChanged();
                Log.d("DEBUG", lvrecette.toString());
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(getApplicationContext(), fault.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void setloading() {
        progress = new ProgressDialog(this);
        // progress.setTitle("Loading");
        progress.setMessage("Chargement...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
// To dismiss the dialog
        //progress.dismiss();

    }


}