package com.example.thodlydugue.saveurslocales;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CategorieMenuActivity extends AppCompatActivity {

    ListView list;
    String titles [] = {"Apperitifs/Salades", "Desserts", "Boissons", "Viandes", "Poissons", "Soupes"};
    int imgs [] = {R.drawable.antipasto, R.drawable.cupcake, R.drawable.lemonade, R.drawable.chickenoutline, R.drawable.fish, R.drawable.bowl};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie_menu);

        list = findViewById(R.id.list1);

        MyAdapter adapter = new MyAdapter(this, titles, imgs);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    Intent intent = new Intent(CategorieMenuActivity.this, ApperitifsActivity.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(CategorieMenuActivity.this, DessertsActivity.class);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(CategorieMenuActivity.this, BoissonsActivity.class);
                    startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(CategorieMenuActivity.this, ViandesActivity.class);
                    startActivity(intent);
                }
                if (position == 4) {
                    Intent intent = new Intent(CategorieMenuActivity.this, PoissonsActivity.class);
                    startActivity(intent);
                }
                if (position == 5) {
                    Intent intent = new Intent(CategorieMenuActivity.this, SoupActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String myTitles[];
        int[] imgs;

        public MyAdapter(Context c, String[] titles, int [] images){
            super(c,R.layout.listviewrow, R.id.tv1, titles);
            this.context=c;
            this.imgs=images;
            this.myTitles=titles;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.listviewrow, parent,false);
            ImageView images =row.findViewById(R.id.logo);
            TextView myTitle = row.findViewById(R.id.tv1);
            images.setImageResource(imgs[position]);
            myTitle.setText(titles[position]);
            return row;
        }
    }
}
