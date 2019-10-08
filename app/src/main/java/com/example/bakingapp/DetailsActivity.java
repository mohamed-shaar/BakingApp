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

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        Recipe recipe = intent.getParcelableExtra("recipe_object");
        if (recipe == null){
            Log.d("Tago", " is null");
        }
        else {
            Log.d("Tago", " is not null");
        }
        videoUrls = new ArrayList<>();
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();

        //ingredients.addAll(recipe.getIngredients());
        //steps.addAll(recipe.getSteps());

        for (Ingredient ingredient: recipe.getIngredients()){
            ingredients.add(ingredient);
            Log.d("Tago name", ingredient.getIngredient());
        }
        Log.d("Tago", String.valueOf(ingredients.size()));
        for (Step step: recipe.getSteps()){
            steps.add(step);
            Log.d("Tago", step.getDescription());
        }
        Log.d("Tago", String.valueOf(steps.size()));
        for (Step step: steps){
            videoUrls.add(step.getVideoURL());
        }

        RecipeInformationFragment recipeInformationFragment = new RecipeInformationFragment(this, ingredients, steps);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_details, recipeInformationFragment).commit();
        //fragmentManager.beginTransaction().add(R.id.fragment_details, recipeInformationFragment).commit();
    }
}
