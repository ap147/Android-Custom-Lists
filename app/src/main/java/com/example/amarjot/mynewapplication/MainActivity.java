package com.example.amarjot.mynewapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
        setupFragments();

        // TODO: Figure out how to send parameters to Fragment
    }

    @Override
    protected void onStop() {
        // TODO: Save State
        saveState(selected_Category);
        super.onStop();
    }

    public void setupFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction recipe_list = fragmentManager.beginTransaction();
        RecipeListViewFragment list = new RecipeListViewFragment();

        recipe_list.add(R.id.list_frame, list);
        recipe_list.commit();
    }

    public void setupNav() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

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
                        saveState(selected_Category);
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
            System.out.println(recipe_type);
        }
    }

    protected void setupList (String type) {
        selected_Category = type;
        //loadArray(type);
//
//        list= (ListView) findViewById(R.id.listview);
//        CustomListview customListview = new CustomListview(this, recipe_title, recipe_description, recipe_image_id);
//        list.setAdapter(customListview);
//
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                displayRecipeActivity(view, i);
//            }
//        });
    }

    protected void saveState (String recipeType) {
        System.out.println("Saving STATE : " + recipeType);
        sharedPreferences = getSharedPreferences("recipeType", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("recipeType", recipeType);
        editor.apply();
    }


}
