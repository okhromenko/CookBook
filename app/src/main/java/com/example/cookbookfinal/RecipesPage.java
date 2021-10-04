package com.example.cookbookfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipesPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_page);

        ConstraintLayout resipesBg = findViewById(R.id.ResipesPageBg);
        ImageView resipesImage = findViewById(R.id.ResipesPageImage);
        TextView resipesTitle = findViewById(R.id.ResipesPageTitle);
        TextView resipesDate = findViewById(R.id.recipesPageDate);
        TextView resipesLevel = findViewById(R.id.recipesPageLevel);
        TextView resipesText = findViewById(R.id.RecipesPageText);

        resipesBg.setBackgroundColor(getIntent().getIntExtra("resipesBg", 0));
        resipesImage.setImageResource(getIntent().getIntExtra("resipesImage", 0));
        resipesTitle.setText(getIntent().getStringExtra("resipesTitle"));
        resipesDate.setText(getIntent().getStringExtra("resipesDate"));
        resipesLevel.setText(getIntent().getStringExtra("resipesLevel"));
        resipesText.setText(getIntent().getStringExtra("resipesText"));
    }


    public void OpenRecipes(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void OpelPersonalAcc(View view){
        Intent intent = new Intent(this, Personal_acc.class);
        startActivity(intent);
    }
}