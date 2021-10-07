package com.example.cookbookfinal.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbookfinal.Models.Cook;
import com.example.cookbookfinal.R;
import com.example.cookbookfinal.RecipesPage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    Context context;
    List<Cook> request;

    public RequestAdapter(Context context, List<Cook> recipes) {
        this.context = context;
        this.request = recipes;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View requestItems = LayoutInflater.from(context).inflate(R.layout.request_item, parent, false);
        return new RequestAdapter.RequestViewHolder(requestItems);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int imageId = context.getResources().getIdentifier("ic_" + request.get(position).getImage(), "drawable", context.getPackageName());
//        holder.requestImage.setImageResource(imageId);
        Picasso.get().load(request.get(position).getImage()).into(holder.requestImage);

        holder.requestTitle.setText(request.get(position).getName());
        holder.requestDate.setText(request.get(position).getTime());
        holder.requestLevel.setText(request.get(position).getLevel());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecipesPage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)  context,
                        new Pair<View, String >(holder.requestImage, "resipesImage"));
                intent.putExtra("resipesImage", request.get(position).getImage());
                intent.putExtra("resipesTitle", request.get(position).getName());
                intent.putExtra("resipesDate", request.get(position).getTime());
                intent.putExtra("resipesLevel", request.get(position).getLevel());
                intent.putExtra("resipesText", request.get(position).getDescription());
                intent.putExtra("recipeShortDescription", request.get(position).getShortDescription());
//                intent.putExtra("resipesId", recipes.get(position).getId());
                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return request.size();
    }

    public static final class RequestViewHolder extends RecyclerView.ViewHolder{

        ImageView requestImage;
        TextView requestTitle, requestLevel, requestDate;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);

            requestImage = itemView.findViewById(R.id.requestImage);
            requestTitle = itemView.findViewById(R.id.requestTitle);
            requestLevel = itemView.findViewById(R.id.requestLevel);
            requestDate = itemView.findViewById(R.id.requestDate);
        }
    }
}
