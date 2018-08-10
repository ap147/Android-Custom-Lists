package com.example.amarjot.mynewapplication;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.mikhaellopez.circularimageview.CircularImageView;

public class DisplayMessageActivity extends AppCompatActivity {

    protected String recipe_title;
    protected int recipe_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        setRecipeDetails();
        setupActionbar();
    }

    private void setupActionbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24px);

        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle(recipe_title);
    }
    private void setRecipeDetails() {

        Bundle bundle = getIntent().getExtras();
        recipe_title = bundle.getString("recipe_title");
        recipe_image = bundle.getInt("recipe_image");

        ImageView img = findViewById(R.id.imageView);
        img.setImageResource(recipe_image);

    }
}
