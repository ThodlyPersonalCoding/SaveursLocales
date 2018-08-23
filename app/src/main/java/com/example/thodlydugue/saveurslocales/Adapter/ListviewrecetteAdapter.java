package com.example.thodlydugue.saveurslocales.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thodlydugue.saveurslocales.Modele.recettes;
import com.example.thodlydugue.saveurslocales.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListviewrecetteAdapter extends ArrayAdapter<recettes> {
    // View lookup cache
    private static class ViewHolder {
        TextView textrecette;
        ImageView imageView;
        ImageView imageView1;
        TextView txtdesc;
    }
    public ListviewrecetteAdapter(Context context, ArrayList<recettes> users) {
        super(context, R.layout.recette_layout, users);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        recettes r = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for listviewrow
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.recette_layout, parent, false);
            viewHolder.textrecette = (TextView) convertView.findViewById(R.id.txtrecette);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imrecette);
            viewHolder.txtdesc=(TextView) convertView.findViewById(R.id.txtdescription);
            viewHolder.imageView1 = (ImageView) convertView.findViewById(R.id.imrecette1);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.

        viewHolder.textrecette.setText(r.getNom_recette());
        Picasso.with(getContext()).load(r.getImage_recette()).resize(240, 120).into(viewHolder.imageView);
        viewHolder.txtdesc.setText(r.getDescription());
        Picasso.with(getContext()).load(r.getImage_recette1()).resize(150, 50).into(viewHolder.imageView1);

        // viewHolder.home.setText(user.hometown);
        // Return the completed view to render on screen.
        //viewHolder.textrecette.setText(recettes.fromListMap()+);
        return convertView;
    }

}




