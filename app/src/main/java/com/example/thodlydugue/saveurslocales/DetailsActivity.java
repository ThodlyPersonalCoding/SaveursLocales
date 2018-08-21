package com.example.thodlydugue.saveurslocales;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.thodlydugue.saveurslocales.Account.LoginActivity;
import com.example.thodlydugue.saveurslocales.Modele.recettes;
import com.squareup.picasso.Picasso;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
    public static final String AplicationID="0194C491-E19B-FE7A-FFBD-B3BBAC63CE00";
    public static final String SecretKey="006C1FAB-E51B-1553-FF78-0252E07EA900";

    TextView tvRecette;
    TextView tvIngredients;
    TextView tvpreparation;
    ImageView ivImage;
    FloatingActionButton btshare;
    FloatingActionButton btnfav;

    recettes recette;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbardetails);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_backbutton1);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //retrieve recette that's been 'sent' from main activity

        recette = (recettes) getIntent().getSerializableExtra("recettes");

        //retrieve all fields and set their value
        tvRecette = ButterKnife.findById(this, R.id.titlerecette);
        tvRecette.setText(recette.getNom_recette());

        tvIngredients = ButterKnife.findById(this, R.id.tvIngredients);
        tvIngredients.setText(recette.getIngredients());

        tvpreparation = ButterKnife.findById(this, R.id.preparation);
        tvpreparation.setText(recette.getPreparation());


        ivImage = ButterKnife.findById(this, R.id.ivrecette);


        final String text =  recette.getNom_recette()+ " " +recette.getIngredients()+ "" +recette.getPreparation();
        Picasso.with(this).load(recette.getImage_recette())
                .into(ivImage);
        btshare = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        btshare.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        text + ""+ "Pour plus de recettes: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        btnfav=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        btnfav.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                savefavorite();

            }
        });
    }



    public void savefavorite() {
        Backendless.initApp(getApplicationContext(),AplicationID,SecretKey);
        //recettes recette = (recettes) getIntent().getSerializableExtra("recettes");

        HashMap testObject = new HashMap<>();
        testObject.put("nomrecette", recette.getNom_recette());
        testObject.put("imagerecette", recette.getImage_recette());
        testObject.put("ingredient", recette.getIngredients());
        testObject.put("preparation", recette.getPreparation());
        testObject.put("id_user", LoginActivity.idUser);

        if (LoginActivity.idUser != null) {
            Backendless.Data.of("favorites").save(testObject, new AsyncCallback<Map>() {
                @Override
                public void handleResponse(Map response) {

                    Toast.makeText(DetailsActivity.this, "Succes", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.e("MYAPP", "Server reported an error " + fault.getMessage());
                    Toast.makeText(getApplicationContext(), "You clicked on YES" +fault.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
        else {
            Toast.makeText(DetailsActivity.this, "vous devez connecter, ou creer un compte", Toast.LENGTH_SHORT).show();

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailsActivity.this);
            alertDialog.setTitle("Connexion");
            alertDialog.setMessage("Vous devez etre connecté");
            //alertDialog.setIcon(R.drawable.ic_launcher);

            alertDialog.setNegativeButton("NON",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,  int which) {
                            Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            // tv.setText("No Button clicked");
                        }
                    });
            alertDialog.setPositiveButton("OUI",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog
                            Intent intent=new Intent(DetailsActivity.this,LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                            //tv.setText("Yes Button clicked");
                        }
                    });
            alertDialog.show();
        }
    }
}
