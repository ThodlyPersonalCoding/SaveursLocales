package com.example.thodlydugue.saveurslocales.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thodlydugue.saveurslocales.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewRecetteAdapter extends RecyclerView.Adapter<RecyclerViewRecetteAdapter.ViewHolder>{
    // The items to display in your RecyclerView
    private static List<com.example.thodlydugue.saveurslocales.Modele.recettes> recettes;
    private static Context context;


    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewRecetteAdapter(Context context, ArrayList<com.example.thodlydugue.saveurslocales.Modele.recettes> recettes) {
        this.recettes = recettes;
        context = context;
    }

    @Override
    public RecyclerViewRecetteAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recette_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewRecetteAdapter.ViewHolder viewHolder, int i) {

        viewHolder.txtrecette.setText(recettes.get(i).getNom_recette());
        Picasso.with(context).load(recettes.get(i).getImage_recette()).resize(240, 120).into(viewHolder.imrecette);
    }

    @Override
    public int getItemCount() {
        return recettes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtrecette;
        private ImageView imrecette;
        public ViewHolder(View view) {
            super(view);

            txtrecette = (TextView)view.findViewById(R.id.txtrecette);
            imrecette = (ImageView) view.findViewById(R.id.imrecette);
        }
    }


}
