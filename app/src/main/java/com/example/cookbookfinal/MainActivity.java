package com.example.cookbookfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;

import com.example.cookbookfinal.Models.Category;
import com.example.cookbookfinal.Models.Cook;
import com.example.cookbookfinal.Models.User;
import com.example.cookbookfinal.adapter.CategoryAdapter;
import com.example.cookbookfinal.adapter.RecipesAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.List;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static List<Cook> recipesList = new ArrayList<>();
    static List<Cook> fullrecipesList = new ArrayList<>();
    public FirebaseAuth mAuth;
    public DatabaseReference myRef;
    RecyclerView categoryRecycle, resipesRecycle;
    CategoryAdapter categoryAdapter;
    static RecipesAdapter recipesAdapter;
    ImageView buttonCategoryAll;
    DatabaseReference mdatabaseref;
    static List<Category> CategoryList = new ArrayList<>();
    List<String> listData = new ArrayList<>();
    List <Category> listTemp = new ArrayList<>();
    List <Cook> listCook = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser UseruID = mAuth.getInstance().getCurrentUser();

        mdatabaseref = FirebaseDatabase.getInstance().getReference("Users");
        mdatabaseref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()){
                    User value = ds.getValue(User.class);
                    Boolean role = value.getRole();

                    String tt = UseruID.getUid();

                    if (ds.getKey().toString().equals(tt) && role == true) {
                        ImageView view = findViewById(R.id.OpenSettings);
                        view.setVisibility(View.VISIBLE);
                    };
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mdatabaseref = FirebaseDatabase.getInstance().getReference("Category");
        mdatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()){
                    Category value = ds.getValue(Category.class);

                    listData.add(value.getName());
                    listTemp.add(value);
                }
                CategoryList.clear();
                CategoryList.addAll(listTemp);
                setCategoryRecycler(CategoryList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mdatabaseref = FirebaseDatabase.getInstance().getReference("Cook");
        mdatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()){
                    Cook value = ds.getValue(Cook.class);

                    listCook.add(value);
                }
                recipesList.clear();
                recipesList.addAll(listCook);
                setCategoryRecycler(CategoryList);
                fullrecipesList.clear();
                fullrecipesList.addAll(recipesList);
                setResipesRecycler(recipesList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Мясо"));
        categoryList.add(new Category("Рыба"));
        categoryList.add(new Category("Сладости"));
        categoryList.add(new Category("Супы"));
        categoryList.add(new Category("Прочее"));

            }
        });




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

    public void Request(View view){
        Intent intent = new Intent(this, Request.class);
        startActivity(intent);
    }
}