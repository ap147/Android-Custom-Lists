package com.example.amarjot.mynewapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class RecipeDetailsFragment extends Fragment{

    protected String recipe_title;
    protected int recipe_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recipe_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        setRecipeDetails();
    }

    private void setRecipeDetails() {

//        ImageView img = getView().findViewById(R.id.imageView);
//        img.setImageResource(recipe_image);

        TextView ingredient_1 = getView().findViewById(R.id.TextView_Recipe_Ingredient1);
        TextView ingredient_2 = getView().findViewById(R.id.TextView_Recipe_Ingredient2);
        TextView ingredient_3 = getView().findViewById(R.id.TextView_Recipe_Ingredient3);
        TextView ingredient_4 = getView().findViewById(R.id.TextView_Recipe_Ingredient4);
        TextView ingredient_5 = getView().findViewById(R.id.TextView_Recipe_Ingredients5);

        ingredient_1.setText("• 1 medium onion, diced");
        ingredient_2.setText("• 2 tablespoons sugar");
        ingredient_3.setText("• 4 cups cheese");
        ingredient_4.setText("• 300g red chili powder");
        ingredient_5.setText("• 2 basil leafs");

        //https://unicode-table.com/en/

        TextView direction_1 = getView().findViewById(R.id.TextView_Recipe_Directions1);
        TextView direction_2 = getView().findViewById(R.id.TextView_Recipe_Directions2);
        TextView direction_3 = getView().findViewById(R.id.TextView_Recipe_Directions3);
        TextView direction_4 = getView().findViewById(R.id.TextView_Recipe_Directions4);
        TextView direction_5 = getView().findViewById(R.id.TextView_Recipe_Directions5);

        direction_1.setText("Cook noodles according to package directions; drain. Meanwhile, in a Dutch oven, cook sausage, beef and onion over medium heat 8-10 minutes or until meat is no longer pink, breaking up meat into crumbles. Add garlic; cook 1 minute. Drain.");
        direction_2.setText("Stir in tomatoes, tomato paste, water, sugar, 3 tablespoons parsley, basil, fennel, 1/2 teaspoon salt and pepper; bring to a boil. Reduce heat; simmer, uncovered, 30 minutes, stirring occasionally.");
        direction_3.setText("In a small bowl, mix egg, ricotta cheese, and remaining parsley and salt.");
        direction_4.setText("Preheat oven to 375°. Spread 2 cups meat sauce into an ungreased 13x9-in. baking dish. Layer with three noodles and a third of the ricotta mixture. Sprinkle with 1 cup mozzarella cheese and 2 tablespoons Parmesan cheese. Repeat layers twice. Top with remaining meat sauce and cheeses (dish will be full).");
        direction_5.setText("Bake, covered, 25 minutes. Bake, uncovered, 25 minutes longer or until bubbly. Let stand 15 minutes before serving.");
    }
}
