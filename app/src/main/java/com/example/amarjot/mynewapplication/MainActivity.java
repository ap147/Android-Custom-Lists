package com.example.amarjot.mynewapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    ListView list;

    String [] recipe_title, recipe_description;
    Integer [] recipe_image_id;

    String selected_Category;

    private DrawerLayout mDrawerLayout;

    public static final String EXTRA_MESSAGE = "com.example.amarjot.mynewapplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNav();
        setSharedPreferences();

        // TODO: By default select Breakfast
    }

    @Override
    protected void onStop() {
        // TODO: Save State
        saveState(selected_Category);
        super.onStop();
    }

    public void setupNav() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24px);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // TODO: Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        selected_Category = menuItem.getTitle().toString();
                        setupList(selected_Category);
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setSharedPreferences () {

        sharedPreferences = getSharedPreferences("recipeType", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("recipeType"))
        {
            String recipe_type = sharedPreferences.getString("recipeType", "");
            setupList(recipe_type);
        }
    }

    protected void setupList (String type) {

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

    protected void displayRecipeActivity(View view, int position) {

        Intent Recipe = new Intent(MainActivity.this, DisplayMessageActivity.class);
        int message = position;
        Recipe.putExtra(EXTRA_MESSAGE,message);
        startActivity(Recipe);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
    }

    protected void loadArray (String type) {

        switch (type)
        {
            case "Breakfast" :
                recipe_title = new String[] {
                        getString(R.string.breakfast_title_1),
                        getString(R.string.breakfast_title_2),
                        getString(R.string.breakfast_title_3),
                        getString(R.string.breakfast_title_4),
                        getString(R.string.breakfast_title_5)};

                recipe_description= new String[] {
                        getString(R.string.breakfast_description_1),
                        getString(R.string.breakfast_description_2),
                        getString(R.string.breakfast_description_3),
                        getString(R.string.breakfast_description_4),
                        getString(R.string.breakfast_description_5)};

                recipe_image_id= new Integer[] {
                        R.drawable.oats,
                        R.drawable.weetbix,
                        R.drawable.pancakes,
                        R.drawable.water,
                        R.drawable.blueberry_smoothie};
                        break;

            case "Lunch" :
                recipe_title = new String[] {
                        getString(R.string.lunch_title_1),
                        getString(R.string.lunch_title_2),
                        getString(R.string.lunch_title_3),
                        getString(R.string.lunch_title_4),
                        getString(R.string.lunch_title_5)};

                recipe_description= new String[] {
                        getString(R.string.lunch_description_1),
                        getString(R.string.lunch_description_2),
                        getString(R.string.lunch_description_3),
                        getString(R.string.lunch_description_4),
                        getString(R.string.lunch_description_5)};

                recipe_image_id= new Integer[] {
                        R.drawable.date_scones,
                        R.drawable.avocado_toast,
                        R.drawable.guacamole,
                        R.drawable.hummus_crackers,
                        R.drawable.mini_pizzas};
                        break;

            case "Dinner" :
                recipe_title = new String[] {
                        getString(R.string.dinner_title_1),
                        getString(R.string.dinner_title_2),
                        getString(R.string.dinner_title_3),
                        getString(R.string.dinner_title_4),
                        getString(R.string.dinner_title_5)};

                recipe_description= new String[] {
                        getString(R.string.dinner_description_1),
                        getString(R.string.dinner_description_2),
                        getString(R.string.dinner_description_3),
                        getString(R.string.dinner_description_4),
                        getString(R.string.dinner_description_5)};

                recipe_image_id= new Integer[] {
                        R.drawable.thai_pumpkin_soup,
                        R.drawable.canaloni,
                        R.drawable.lasanga_vege,
                        R.drawable.wraps,
                        R.drawable.nachos};
                        break;
        }

    }


    protected void saveState (String recipeType) {

        sharedPreferences = getSharedPreferences("recipeType", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("recipeType", recipeType);
        editor.apply();
    }

}
