package com.example.thodlydugue.saveurslocales.Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class categories {


    private String nom_categorie;
    private String objectId;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    public String getNom_categorie() {
        return nom_categorie;
    }
    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }
    public static ArrayList<categories> fromListMap(List<Map> map) {

        ArrayList<categories> categories = new ArrayList();

        for(int i = 0; i < map.size(); i++) {
            categories c = new categories();
            c.setObjectId((String) map.get(i).get("ObjectId"));
            categories.add(c);
        }

        return categories;
    }


}
