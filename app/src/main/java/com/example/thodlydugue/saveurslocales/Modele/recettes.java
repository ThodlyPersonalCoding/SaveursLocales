package com.example.thodlydugue.saveurslocales.Modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class recettes implements Serializable{


    private String nom_recette;
    private String image_recette;
    private String image_recette1;
    private categories id_categorie;
    private String description;
    private String ingredients;

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    private String preparation;


    public recettes(){
        this.nom_recette=getNom_recette();
        this.image_recette=getImage_recette();
        this.image_recette1=getImage_recette1();
    }


    public String getNom_recette() {
        return nom_recette;
    }

    public void setNom_recette(String nom_recette) {
        this.nom_recette = nom_recette;
    }

    public String getImage_recette() {
        return image_recette;
    }

    public void setImage_recette(String image_recette) {
        this.image_recette = image_recette;
    }
    public String getImage_recette1() {
        return image_recette1;
    }

    public void setImage_recette1(String image_recette1) {
        this.image_recette1 = image_recette1;
    }

    public categories getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(categories id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static ArrayList<recettes> fromListMap(List<Map> map) {

        ArrayList<recettes> recette = new ArrayList();

        for(int i = 0; i < map.size(); i++) {
            recettes p = new recettes();
            p.setNom_recette((String) map.get(i).get("nom_recette"));
            p.setImage_recette((String) map.get(i).get("image_recette"));
            p.setDescription((String) map.get(i).get("description"));
            p.setIngredients((String) map.get(i).get("ingredients"));
            p.setPreparation((String) map.get(i).get("preparation"));
            p.setImage_recette1((String) map.get(i).get("image_recette1"));
            recette.add(p);
        }
        return recette;
    }






}
