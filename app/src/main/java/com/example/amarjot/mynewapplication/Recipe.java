package com.example.amarjot.mynewapplication;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private String name;
    private String description;
    private String category;
    private String photoName;

    private List ingredients = new ArrayList();
    private List directions = new ArrayList();

    private List tags = new ArrayList();

    public Recipe(String recipeName){
        this.name = recipeName;
    }

    public Recipe(String _category, String _description , String _name, String _photoName,
                  List _ingredients, List _directions, List _tags){

        this.name = _name;
        this.description = _description;
        this.category = _category;
        this.photoName = _photoName;

        this.ingredients = _ingredients;
        this.directions = _directions;
        this.tags = _tags;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getPhotoName() {
        return photoName;
    }


    public List getIngredients() {
        return ingredients;
    }

    public List getDirections() {
        return directions;
    }

    public List getTags() {
        return tags;
    }


}