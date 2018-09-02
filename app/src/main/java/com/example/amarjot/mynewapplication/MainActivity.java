package com.example.amarjot.mynewapplication;

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

public class MainActivity extends AppCompatActivity {

    String selected_Category;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Sets up hamburger navigation drawer
        setupNav();

        // savedInstanceState has saved values if user tilted screen
        if (savedInstanceState == null) {
            // Slide out navigation drawer
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        else {
            // Use saved value to populate recipe fragment
            selected_Category = savedInstanceState.getString("selected_Category");
            setupFragment(selected_Category);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putString("selected_Category", selected_Category);
        super.onSaveInstanceState(savedInstanceState);
    }

    // Add a list of recipes main activity.
    public void setupFragment(String type) {
        // Make bundle with what recipe category to show, passed to listview Fragment
        Bundle bundle = new Bundle();
        bundle.putString("selected_Category", type);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction recipe_list = fragmentManager.beginTransaction();
        Fragment list = new RecipeListViewFragment();

        list.setArguments(bundle);
        recipe_list.replace(R.id.list_frame, list);
        recipe_list.commit();
    }


    public void setupNav() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        // User can click this to see options (Breakfast, Lunch, Dinner)
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24px);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        selected_Category = menuItem.getTitle().toString();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction recipe_list = fragmentManager.beginTransaction();
                        Fragment list = new Fragment();
                        recipe_list.replace(R.id.list_frame, list);
                        recipe_list.commit();

                        setupFragment(selected_Category);
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
}
