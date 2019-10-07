package com.example.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.DetailsActivity;
import com.example.bakingapp.R;
import com.example.bakingapp.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> {

    private Context context;
    private ArrayList<Recipe> recipes = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();

    public RecipeListAdapter() {
    }

    public RecipeListAdapter(Context context, ArrayList<Recipe> recipes, ArrayList<String> images) {
        this.context = context;
        this.recipes = recipes;
        this.images = images;
    }

    @NonNull
    @Override
    public RecipeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_list_item, parent, false);
        return new RecipeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListViewHolder holder, final int position) {
        String title = recipes.get(position).getName();
        String image_url = images.get(position);

        holder.tv_recipe_title.setText(title);
        Picasso.get().load(image_url).fit().centerCrop().into(holder.iv_recipe_image);

        holder.cv_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("recipe_object", recipes.get(position));

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class RecipeListViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_recipe_image;
        public TextView tv_recipe_title;
        public CardView cv_holder;

        public RecipeListViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_recipe_image = itemView.findViewById(R.id.iv_recipe);
            tv_recipe_title = itemView.findViewById(R.id.tv_recipe);
            cv_holder = itemView.findViewById(R.id.cv_recipe);
        }
    }
}
