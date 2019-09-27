package com.example.bakingapp.api;

import com.example.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeApi {

    @GET("baking.json")
    Call<List<Recipe>> getAllRecipes();
}
