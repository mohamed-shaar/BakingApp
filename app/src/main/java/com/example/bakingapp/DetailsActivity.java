package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.model.Ingredient;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Step;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private ArrayList<String> videoUrls;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Step> steps;
    private int Index;
    private boolean tabletMode;

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        Recipe recipe = intent.getParcelableExtra("recipe_object");
        videoUrls = new ArrayList<>();
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();

        ingredients = intent.getParcelableArrayListExtra("ingredient_object");
        steps = intent.getParcelableArrayListExtra("step_list");
        for (Step step: steps){
            videoUrls.add(step.getVideoURL());
            Log.d("Array step", step.getShortDescription());
        }
        for (Ingredient ingredient: ingredients){
            Log.d("Array ingredient", ingredient.getQuantity() + " " + ingredient.getMeasure() + " " + ingredient.getIngredient());
        }

        if (findViewById(R.id.fragment_video) != null){
            tabletMode = true;
        }
        else {
            tabletMode = false;
        }
        Log.d("mode", String.valueOf(tabletMode));
        RecipeInformationFragment recipeInformationFragment = new RecipeInformationFragment(this, ingredients, steps, videoUrls, tabletMode);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_details, recipeInformationFragment).commit();
    }
}
