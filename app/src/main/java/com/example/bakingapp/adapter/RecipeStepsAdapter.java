package com.example.bakingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Step;

import java.util.ArrayList;

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.RecipeStepViewHolder>{

    private Context context;
    private ArrayList<Step> steps;

    public RecipeStepsAdapter(Context context, ArrayList<Step> steps) {
        this.context = context;
        this.steps = steps;
    }

    public RecipeStepsAdapter() {
    }

    @NonNull
    @Override
    public RecipeStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.step_item, parent, false);
        return new RecipeStepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepViewHolder holder, int position) {
        String id = String.valueOf(steps.get(position).getId());
        String description = steps.get(position).getDescription();

        //holder.tv_recipe_step_id.setText(id);
        holder.tv_recipe_step_description.setText(description);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class RecipeStepViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_recipe_step_id;
        public TextView tv_recipe_step_description;

        public RecipeStepViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_recipe_step_id = itemView.findViewById(R.id.tv_recipe_step_id);
            tv_recipe_step_description = itemView.findViewById(R.id.tv_recipe_step_description);
        }
    }
}
