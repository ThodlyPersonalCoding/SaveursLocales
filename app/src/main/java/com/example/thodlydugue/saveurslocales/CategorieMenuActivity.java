package com.example.thodlydugue.saveurslocales;

import android.content.Context;
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
import android.widget.Toast;
import android.graphics.Color;

public class CategorieMenuActivity extends AppCompatActivity {

    ListView list;
    String titles [] = {"Soupes", "Desserts", "Boissons", "Viandes", "Apperitifs", "Poissons"};
    int imgs [] = {R.drawable.bowl, R.drawable.cupcake, R.drawable.lemonade, R.drawable.chickenoutline, R.drawable.antipasto, R.drawable.fish};

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
                    Toast.makeText(CategorieMenuActivity.this, "Bonjour1", Toast.LENGTH_SHORT).show();
                }
                if (position == 1) {
                    Toast.makeText(CategorieMenuActivity.this, "Bonjour2", Toast.LENGTH_SHORT).show();
                }
                if (position == 2) {
                    Toast.makeText(CategorieMenuActivity.this, "Bonjour3", Toast.LENGTH_SHORT).show();
                }
                if (position == 3) {
                    Toast.makeText(CategorieMenuActivity.this, "Bonjour4", Toast.LENGTH_SHORT).show();
                }
                if (position == 4) {
                    Toast.makeText(CategorieMenuActivity.this, "Bonjour5", Toast.LENGTH_SHORT).show();
                }
                if (position == 5) {
                    Toast.makeText(CategorieMenuActivity.this, "Bonjour6", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String myTitles[];
        int[] imgs;

        public MyAdapter(Context c, String[] titles, int [] images){
            super(c,R.layout.row, R.id.tv1, titles);
            this.context=c;
            this.imgs=images;
            this.myTitles=titles;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent,false);
            ImageView images =row.findViewById(R.id.logo);
            TextView myTitle = row.findViewById(R.id.tv1);
            images.setImageResource(imgs[position]);
            myTitle.setText(titles[position]);
            return row;
        }
    }
}
