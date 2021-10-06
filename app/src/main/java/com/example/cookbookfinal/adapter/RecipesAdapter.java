package com.example.cookbookfinal.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbookfinal.R;
import com.example.cookbookfinal.RecipesPage;
import com.example.cookbookfinal.Models.Cook;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {

    Context context;
    List<Cook> recipes;

    public RecipesAdapter(Context context, List<Cook> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recipesItems = LayoutInflater.from(context).inflate(R.layout.recipes_item, parent, false);
        return new RecipesAdapter.RecipesViewHolder(recipesItems);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, @SuppressLint("RecyclerView") int position) {

        int imageId = context.getResources().getIdentifier("ic_" + recipes.get(position).getImage(), "drawable", context.getPackageName());
        holder.recipesImage.setImageResource(imageId);

        holder.recipesTitle.setText(recipes.get(position).getName());
        holder.recipesDate.setText(recipes.get(position).getTime());
        holder.recipesLevel.setText(recipes.get(position).getLevel());
        holder.recipesShortDescription.setText(recipes.get(position).getShortDescription());

        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecipesPage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)  context,
                        new Pair<View, String >(holder.recipesImage, "resipesImage"));
                intent.putExtra("resipesImage", imageId);
                intent.putExtra("resipesTitle", recipes.get(position).getName());
                intent.putExtra("resipesDate", recipes.get(position).getTime());
                intent.putExtra("resipesLevel", recipes.get(position).getLevel());
                intent.putExtra("resipesText", recipes.get(position).getDescription());
//                intent.putExtra("resipesId", recipes.get(position).getId());
                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static final class RecipesViewHolder extends RecyclerView.ViewHolder{
        CardView recipesBg;
        ImageView recipesImage;
        TextView recipesTitle, recipesDate, recipesLevel, recipesShortDescription;

        public RecipesViewHolder(@NonNull View itemView) {
            super(itemView);

            recipesBg = itemView.findViewById(R.id.recipesBg);
            recipesImage = itemView.findViewById(R.id.recipesImage);
            recipesTitle = itemView.findViewById(R.id.recipeTitle);
            recipesDate = itemView.findViewById(R.id.recipesDate);
            recipesLevel = itemView.findViewById(R.id.recipesLevel);
            recipesShortDescription = itemView.findViewById(R.id.recipeShortDescription);
        }
    }
}

