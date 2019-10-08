package com.example.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.adapter.RecipeIngredientsAdapter;
import com.example.bakingapp.adapter.RecipeStepsAdapter;
import com.example.bakingapp.model.Ingredient;
import com.example.bakingapp.model.Step;

import java.util.ArrayList;

public class RecipeInformationFragment extends Fragment {
    private RecyclerView rv_ingredients;
    private RecyclerView rv_steps;

    private ArrayList<Ingredient> ingredients;
    private ArrayList<Step> steps;

    private RecipeIngredientsAdapter ingredientsAdapter;
    private RecipeStepsAdapter stepsAdapter;
    private Context context;


    public RecipeInformationFragment(Context context,ArrayList<Ingredient> ingredients, ArrayList<Step> steps) {
        this.context = context;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public RecipeInformationFragment(){}



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        rv_ingredients = view.findViewById(R.id.rv_recipe_ingredients);
        rv_steps = view.findViewById(R.id.rv_recipe_steps);

        rv_ingredients.setLayoutManager(new LinearLayoutManager(context));
        rv_steps.setLayoutManager(new LinearLayoutManager(context));

        rv_ingredients.setHasFixedSize(false);
        rv_steps.setHasFixedSize(false);

        ingredientsAdapter = new RecipeIngredientsAdapter(context, ingredients);
        stepsAdapter = new RecipeStepsAdapter(context, steps);

        rv_ingredients.setAdapter(ingredientsAdapter);
        rv_steps.setAdapter(stepsAdapter);

        Log.d("Tago Fragment", String.valueOf(ingredients.size() + String.valueOf(steps.size())));
        return view;
    }
}
