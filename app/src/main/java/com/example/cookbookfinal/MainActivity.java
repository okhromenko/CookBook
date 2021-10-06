package com.example.cookbookfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;

import com.example.cookbookfinal.Models.Category;
import com.example.cookbookfinal.Models.Cook;
import com.example.cookbookfinal.adapter.CategoryAdapter;
import com.example.cookbookfinal.adapter.RecipesAdapter;

import java.util.List;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    static List<Cook> recipesList = new ArrayList<>();
    static List<Cook> fullrecipesList = new ArrayList<>();


    RecyclerView categoryRecycle, resipesRecycle;
    CategoryAdapter categoryAdapter;
    static RecipesAdapter recipesAdapter;
    ImageView buttonCategoryAll;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Мясо"));
        categoryList.add(new Category("Рыба"));
        categoryList.add(new Category("Сладости"));
        categoryList.add(new Category("Супы"));
        categoryList.add(new Category("Прочее"));

        setCategoryRecycler(categoryList);


        recipesList.clear();
        fullrecipesList.clear();


//        Category category = new Category("Рыба");
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//        databaseReference.child("Category").push().setValue(category, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//            }
//        });


        recipesList.add(new Cook("Бфстурма", "Бастурма", "Меять", "python_3", "33 дня",
                "амам", true, "#9FA52D", "Мясо"));

        fullrecipesList.addAll(recipesList);
        setResipesRecycler(recipesList);

        buttonCategoryAll = (ImageView) findViewById(R.id.category_recipes);


        buttonCategoryAll.setOnClickListener(this);
    }

    private void setCategoryRecycler(List<Category> categoryList){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycle = findViewById(R.id.categoryRecycler);
        categoryRecycle.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycle.setAdapter(categoryAdapter);
    }

    public static void ShowRecipesByCategory(String category){

        recipesList.clear();
        recipesList.addAll(fullrecipesList);

        List<Cook> filterResipes = new ArrayList<>();

        for (Cook c : recipesList){
            if (c.getCategory().equals(category)){
                filterResipes.add(c);
            }
        }
            recipesList.clear();
            recipesList.addAll(filterResipes);

            recipesAdapter.notifyDataSetChanged();
    }

    private void setResipesRecycler(List<Cook> recipesList){

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

    public void OpelPersonalAcc(View view){
        Intent intent = new Intent(this, Personal_acc.class);
        startActivity(intent);
    }

    public void AddResipes(View view){
        Intent intent = new Intent(this, AddResipes.class);
        startActivity(intent);
    }

    public void SettingsPersonal(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}