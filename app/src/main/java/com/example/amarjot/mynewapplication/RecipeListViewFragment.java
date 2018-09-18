package com.example.amarjot.mynewapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecipeListViewFragment extends Fragment{

    String selected_Category;

    ListView list;

    List recipes = new ArrayList();

    String [] recipe_title, recipe_description;
    Integer [] recipe_image_id;

    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        selected_Category = getArguments().getString("selected_Category");
        return inflater.inflate(R.layout.recipe_listview, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        setupList(selected_Category);
    }

    protected void setupList (String type) {
        selected_Category = type;
        loadArray(type);

        list= getView().findViewById(R.id.listView);
        CustomListview customListview = new CustomListview(getContext(), recipe_title, recipe_description, recipe_image_id);
        list.setAdapter(customListview);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                displayRecipeDetails(i);
            }
        });
    }
    protected void displayRecipeDetails(int position) {
        Intent intent = new Intent(getActivity(), DisplayMessageActivity.class);
        Bundle recipe_details = new Bundle();
        recipe_details.putString(getString(R.string.pass_recipe_title), recipe_title[position]);
        recipe_details.putInt(getString(R.string.pass_recipe_image), recipe_image_id[position]);
        intent.putExtras(recipe_details);
        startActivity(intent);
        getActivity().overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
    }

    protected void loadArray (String type) {

        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("recipes");
        // Attach a listener to read the data at our posts reference
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot: dataSnapshot.getChildren())
                {
                    // How to parse JSON : //http://theoryapp.com/parse-json-in-java/
                    JSONObject obj;
                    try {
                        obj = new JSONObject(singleSnapshot.getValue().toString());

                        String recipeName = obj.getString("name");
                        Recipe newRecipe = new Recipe(recipeName);

                        recipes.add(newRecipe);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Recipe x = (Recipe) recipes.get(0);
                    System.out.println("Recipe Name : " + x.getName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


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
                        getString(R.string.lunch_title_3),
                        getString(R.string.lunch_title_4),
                        getString(R.string.lunch_title_5),
                        getString(R.string.lunch_title_2)};

                recipe_description= new String[] {
                        getString(R.string.lunch_description_1),
                        getString(R.string.lunch_description_2),
                        getString(R.string.lunch_description_3),
                        getString(R.string.lunch_description_4),
                        getString(R.string.lunch_description_5)};

                recipe_image_id= new Integer[] {
                        R.drawable.date_scones,
                        R.drawable.guacamole,
                        R.drawable.hummus_crackers,
                        R.drawable.mini_pizzas,
                        R.drawable.avocado_toast};
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
}
