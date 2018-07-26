package com.example.amarjot.mynewapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView list;

    String [] recipe_title;
    String [] recipe_description;
    Integer [] recipe_image_id;

    public static final String EXTRA_MESSAGE = "com.example.amarjot.mynewapplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button breakfastButton = findViewById(R.id.buttonBreakfast);
        breakfastButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                setupList("Breakfast");
            }
        });

        final Button lunchButton = findViewById(R.id.buttonLunch);
        lunchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                setupList("Lunch");
            }
        });

    }

    protected void setupList (String type)
    {
        loadArray(type);

        list= (ListView) findViewById(R.id.listview);
        CustomListview customListview = new CustomListview(this, recipe_title, recipe_description, recipe_image_id);
        list.setAdapter(customListview);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                displayRecipeActivity(view, i);
            }
        });

    }

    protected void displayRecipeActivity(View view, int position)
    {
        Intent Recipe = new Intent(MainActivity.this, DisplayMessageActivity.class);
        String message = recipe_title[position].toString();
        Recipe.putExtra(EXTRA_MESSAGE,message);
        startActivity(Recipe);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
    }

    protected void loadArray (String type)
    {
        switch (type)
        {
            case "Breakfast" :
                recipe_title = new String[]{
                        getString(R.string.breakfast_title_1),
                        getString(R.string.breakfast_title_2),
                        getString(R.string.breakfast_title_3),
                        getString(R.string.breakfast_title_4),
                        getString(R.string.breakfast_title_5)};

                recipe_description= new String[]{
                        getString(R.string.breakfast_description_1),
                        getString(R.string.breakfast_description_2),
                        getString(R.string.breakfast_description_3),
                        getString(R.string.breakfast_description_4),
                        getString(R.string.breakfast_description_5)};

                recipe_image_id= new Integer[]{
                        R.drawable.oats,
                        R.drawable.weetbix,
                        R.drawable.water,
                        R.drawable.water,
                        R.drawable.water
                };
                break;

            case "Lunch" :
                recipe_title = new String[]{
                        getString(R.string.breakfast_title_2),
                        getString(R.string.breakfast_title_3),
                        getString(R.string.breakfast_title_4),
                        getString(R.string.breakfast_title_5)};

                recipe_description= new String[]{
                        getString(R.string.breakfast_description_2),
                        getString(R.string.breakfast_description_3),
                        getString(R.string.breakfast_description_4),
                        getString(R.string.breakfast_description_5)};

                recipe_image_id= new Integer[]{
                        R.drawable.weetbix,
                        R.drawable.water,
                        R.drawable.water,
                        R.drawable.water
                };
                break;

        }

    }

}
