package com.example.cookbookfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;

import com.example.cookbookfinal.adapter.CategoryAdapter;
import com.example.cookbookfinal.adapter.RecipesAdapter;
import com.example.cookbookfinal.model.Recipes;
import com.example.cookbookfinal.model.category;

import java.util.List;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView categoryRecycle, resipesRecycle;
    CategoryAdapter categoryAdapter;
    static RecipesAdapter recipesAdapter;
    static List<Recipes> recipesList = new ArrayList<>();
    static List<Recipes> fullrecipesList = new ArrayList<>();
    ImageView buttonCategoryAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<category> categoryList = new ArrayList<>();
        categoryList.add(new category(1, "Мясо"));
        categoryList.add(new category(2, "Рыба"));
        categoryList.add(new category(3, "Сладости"));
        categoryList.add(new category(4, "Супы"));
        categoryList.add(new category(5, "Прочее"));

        setCategoryRecycler(categoryList);


        recipesList.add(new Recipes(1, "basturma", "Бастурма", "22 дня", "средний", "#424345", "Вкуснейший рецепт", 1));
        recipesList.add(new Recipes(2, "python_3", "Камбала в духовке", "1 час", "начальный", "#9FA52D", "Рецепт будет позже", 2));

        fullrecipesList.addAll(recipesList);
        setResipesRecycler(recipesList);

        buttonCategoryAll = (ImageView) findViewById(R.id.category_recipes);
        buttonCategoryAll.setOnClickListener(this);

    }

    private void setCategoryRecycler(List<category> categoryList){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycle = findViewById(R.id.categoryRecycler);
        categoryRecycle.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycle.setAdapter(categoryAdapter);
    }

    public static void ShowRecipesByCategory(int category){

        recipesList.clear();
        recipesList.addAll(fullrecipesList);

        List<Recipes> filterResipes = new ArrayList<>();

        for (Recipes c : recipesList){
            if (c.getCategory() == category){
                filterResipes.add(c);
            }
        }
            recipesList.clear();
            recipesList.addAll(filterResipes);

            recipesAdapter.notifyDataSetChanged();
    }

    private void setResipesRecycler(List<Recipes> recipesList){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        resipesRecycle = findViewById(R.id.RecyclerRecipes);
        resipesRecycle.setLayoutManager(layoutManager);

        recipesAdapter = new RecipesAdapter(this, recipesList);
        resipesRecycle.setAdapter(recipesAdapter);
    }

    @Override
    public void onClick(View view) {
        recipesList.clear();
        recipesList.addAll(fullrecipesList);
        recipesAdapter.notifyDataSetChanged();
    }

    public void OpenRecipes(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}