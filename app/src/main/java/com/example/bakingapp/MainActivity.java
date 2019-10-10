package com.example.bakingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.adapter.RecipeListAdapter;
import com.example.bakingapp.api.Client;
import com.example.bakingapp.api.RecipeApi;
import com.example.bakingapp.model.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecipeApi recipeApi;
    private ArrayList<Recipe> recipes;

    private RecipeListAdapter adapter;

    @Nullable
    private SimpleIdlingResource simpleIdlingResource;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (simpleIdlingResource == null) {
            simpleIdlingResource = new SimpleIdlingResource();
        }
        return simpleIdlingResource;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeApi = Client.getRetrofit().create(RecipeApi.class);

        ArrayList<String> images = new ArrayList<>();
        images.add("https://www.recipeboy.com/wp-content/uploads/2016/09/No-Bake-Nutella-Pie.jpg");
        images.add("https://img.taste.com.au/5hsR3Ore/w720-h480-cfill-q80/taste/2016/11/classic-chewy-brownie-102727-1.jpeg");
        images.add("https://prods3.imgix.net/images/articles/2017_08/Non-Feature-yellow-cake-chocolate-frosting-recipe-dessert.jpg?auto=format%2Ccompress&dpr=1.25&ixjsv=2.2.3&q=75&w=750");
        images.add("https://timeincsecure-a.akamaihd.net/rtmp_uds/429048911/201801/2066/429048911_5706013914001_5703341808001-vs.jpg?pubId=429048911&videoId=5703341808001");

        recipes = new ArrayList<Recipe>();
        getIdlingResource();
        if (simpleIdlingResource != null) {
            simpleIdlingResource.setIdleState(false);
        }
        getRecipes();

        RecyclerView recipesRecyclerView = findViewById(R.id.rv_recipe_list);
        adapter = new RecipeListAdapter(MainActivity.this, recipes, images);
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipesRecyclerView.setHasFixedSize(false);

        recipesRecyclerView.setAdapter(adapter);


    }

    private void getRecipes() {
        Call<ArrayList<Recipe>> allRecipes = recipeApi.getAllRecipes();

        allRecipes.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                if (!response.isSuccessful()) {
                    int code = response.code();
                    Log.d("Code: ", String.valueOf(code));
                    return;
                } else {

                    ArrayList<Recipe> arrayList = response.body();
                    //Log.d("Size", String.valueOf(recipes.size()));
                    for (Recipe recipe : arrayList) {
                        Log.d("Recipe id:", String.valueOf(recipe.getId()));
                        Log.d("Image url", recipe.getImage());
                        recipes.add(recipe);
                    }
                    adapter.notifyDataSetChanged();
                    //Log.d("Item count", String.valueOf(adapter.getItemCount()));
                    if (simpleIdlingResource != null) {
                        simpleIdlingResource.setIdleState(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Log.d("Failure in request: ", t.getMessage());
                if (simpleIdlingResource != null) {
                    simpleIdlingResource.setIdleState(true);
                }
            }
        });
    }
}
