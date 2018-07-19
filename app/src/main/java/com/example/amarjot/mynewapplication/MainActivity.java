package com.example.amarjot.mynewapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView list;

    String [] breakfastname={"Oats", "Weetbix", "Pan Cakes", "Banana & Blue Berry Smoothie", "Water"};
    String [] breakfastdescription={"this is oats", "this is weetbix", "this is pancakes", "This is Banana & Blue Berry Smoothie", "this is water"};
    Integer [] breakfastid={R.drawable.oats, R.drawable.weetbix, R.drawable.pancakes, R.drawable.bananablueberrysmothie, R.drawable.water };

    String [] lunchname={"Banana & Blue Berry Smoothie", "Water"};
    String [] lunchdescription={"This is Banana & Blue Berry Smoothie", "this is water"};
    Integer [] lunchid={R.drawable.bananablueberrysmothie, R.drawable.water };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
    }

    protected void setupList (String type)
    {
        if (type.equals("breakfast"))
        {
            list= (ListView) findViewById(R.id.listview);
            CustomListview customListview = new CustomListview(this, breakfastname, breakfastdescription, breakfastid);
            list.setAdapter(customListview);
        }
        else if (type.equals("lunch"))
        {
            list= (ListView) findViewById(R.id.listview);
            CustomListview customListview = new CustomListview(this, lunchname, lunchdescription, lunchid);
            list.setAdapter(customListview);
        }

    }

}
