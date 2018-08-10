package com.example.amarjot.mynewapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class RecipeListViewFragment extends Fragment{

    SharedPreferences sharedPreferences;

    String selected_Category;

    ListView list;
    String [] recipe_title, recipe_description;
    Integer [] recipe_image_id;

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
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        setupList(selected_Category);
    }

    protected void setupList (String type) {
        selected_Category = type;
        loadArray(type);

        list= (ListView) getView().findViewById(R.id.listView);
        CustomListview customListview = new CustomListview(getContext(), recipe_title, recipe_description, recipe_image_id);

        list.setAdapter(customListview);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                displayRecipeActivity(view, i);
            }
        });
    }
    protected void displayRecipeActivity(View view, int position) {
//        saveState(selected_Category);
//
//        Intent Recipe = new Intent(DisplayMessageActivity.class);
//        int message = position;
//        Recipe.putExtra(EXTRA_MESSAGE,message);
//        startActivity(Recipe);
//        getActivity().overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
    }

    protected void saveState (String recipeType) {
        System.out.println("Saving STATE : " + recipeType);
        sharedPreferences = getActivity().getSharedPreferences("recipeType", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("recipeType", recipeType);
        editor.apply();
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

}
