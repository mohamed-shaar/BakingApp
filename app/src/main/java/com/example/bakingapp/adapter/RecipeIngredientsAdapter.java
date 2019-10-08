package com.example.bakingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Ingredient;

import java.util.ArrayList;

public class RecipeIngredientsAdapter extends RecyclerView.Adapter<RecipeIngredientsAdapter.RecipeIngredientsViewHolder>{

    private Context context;
    private ArrayList<Ingredient> ingredients;

    public RecipeIngredientsAdapter() {
    }

    public RecipeIngredientsAdapter(Context context, ArrayList<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public RecipeIngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingredient_item, parent, false);
        return new RecipeIngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeIngredientsViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        String allIngredients = ingredient.getQuantity()
                + " "
                + ingredient.getMeasure()
                + " "
                + ingredient.getIngredient();

        holder.tv_recipe_ingredient.setText(allIngredients);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class RecipeIngredientsViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_recipe_ingredient;

        public RecipeIngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_recipe_ingredient = itemView.findViewById(R.id.tv_recipe_ingredient);
        }
    }
}
