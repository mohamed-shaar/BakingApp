package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.api.Client;
import com.example.bakingapp.api.RecipeApi;
import com.example.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecipeApi recipeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeApi = Client.getRetrofit().create(RecipeApi.class);

        Call<List<Recipe>> allRecipes = recipeApi.getAllRecipes();

        allRecipes.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (!response.isSuccessful()){
                    int code = response.code();
                    Log.d("Code: ", String.valueOf(code));
                    return;
                }
                else {
                    List<Recipe> recipes = response.body();
                    for (Recipe recipe: recipes){
                        Log.d("Recipe id:", String.valueOf(recipe.getId()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d("Failure in request: ", t.getMessage());
            }
        });
    }
}
