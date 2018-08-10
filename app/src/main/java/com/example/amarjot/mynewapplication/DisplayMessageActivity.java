package com.example.amarjot.mynewapplication;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

public class DisplayMessageActivity extends AppCompatActivity {

    protected String recipe_title;
    protected int recipe_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        setupActionbar();
        setupFragment();
    }

    public void setupFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction recipe_details = fragmentManager.beginTransaction();
        Fragment details = new RecipeDetailsFragment();

        recipe_details.replace(R.id.fragmentRecipeDetails, details);
        recipe_details.addToBackStack(null);
        recipe_details.commit();
    }

    private void setupActionbar() {

        Bundle bundle = getIntent().getExtras();
        recipe_title = bundle.getString("recipe_title");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24px);

        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle(recipe_title);
    }

}
