package com.example.amarjot.mynewapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView list;

    String [] breakfast_title;
    String [] breakfast_description;
    Integer [] breakfast_id;

    String [] lunch_title;
    String [] lunch_description;
    Integer [] lunch_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadArrays();

        final Button breakfastButton = findViewById(R.id.buttonBreakfast);
        breakfastButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                setupList("breakfast");
            }
        });

        final Button lunchButton = findViewById(R.id.buttonLunch);
        lunchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                setupList("lunch");
            }
        });

        breakfastButton.performClick();
    }

    protected void setupList (String type)
    {

        if (type.equals("breakfast"))
        {
            list= (ListView) findViewById(R.id.listview);
            CustomListview customListview = new CustomListview(this, breakfast_title, breakfast_description, breakfast_id);
            list.setAdapter(customListview);
        }
        else if (type.equals("lunch"))
        {
            list= (ListView) findViewById(R.id.listview);
            CustomListview customListview = new CustomListview(this, lunch_title, lunch_description, lunch_id);
            list.setAdapter(customListview);
        }

    }

    protected void loadArrays ()
    {

        breakfast_title = new String[]{
                getString(R.string.breakfast_title_1),
                getString(R.string.breakfast_title_2),
                getString(R.string.breakfast_title_3),
                getString(R.string.breakfast_title_4),
                getString(R.string.breakfast_title_5)};

        breakfast_description= new String[]{
                getString(R.string.breakfast_description_1),
                getString(R.string.breakfast_description_2),
                getString(R.string.breakfast_description_3),
                getString(R.string.breakfast_description_4),
                getString(R.string.breakfast_description_5)};

        breakfast_id= new Integer[]{
                R.drawable.oats,
                R.drawable.weetbix,
                R.drawable.pancakes,
                R.drawable.water,
                R.drawable.bananablueberrysmothie
                };

        lunch_title= new String[]{"Banana & Blue Berry Smoothie", "Water"};
        lunch_description= new String[]{"This is Banana & Blue Berry Smoothie", "this is water"};
        lunch_id= new Integer[]{R.drawable.bananablueberrysmothie, R.drawable.water};

    }

}
