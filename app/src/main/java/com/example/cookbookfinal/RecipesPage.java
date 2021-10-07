package com.example.cookbookfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookbookfinal.Models.Category;
import com.example.cookbookfinal.Models.Cook;
import com.example.cookbookfinal.Models.User;
import com.example.cookbookfinal.adapter.CategoryAdapter;
import com.example.cookbookfinal.adapter.RecipesAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipesPage extends AppCompatActivity {
    public FirebaseAuth mAuth;
    public DatabaseReference myRef;
    DatabaseReference mdatabaseref;


    static List<Cook> recipesList = new ArrayList<>();
    static List<Cook> fullrecipesList = new ArrayList<>();
    static List<Category> CategoryList = new ArrayList<>();
    List <Cook> listCook = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_page);

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
                        Button AddBdRecipes = findViewById(R.id.AddBdRecipes);
                        Button DeleteBdRecipes = findViewById(R.id.DeleteBdRecipes);
                        view.setVisibility(View.VISIBLE);
                        AddBdRecipes.setVisibility(View.VISIBLE);
                        DeleteBdRecipes.setVisibility(View.VISIBLE);
                    };
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ImageView resipesImage = findViewById(R.id.ResipesPageImage);
        TextView resipesTitle = findViewById(R.id.ResipesPageTitle);
        TextView resipesDate = findViewById(R.id.recipesPageDate);
        TextView resipesLevel = findViewById(R.id.recipesPageLevel);
        TextView resipesText = findViewById(R.id.RecipesPageText);
        TextView resipesShortText = findViewById(R.id.recipesPageLevel2);

        Picasso.get().load(getIntent().getStringExtra("resipesImage")).into(resipesImage);
        resipesTitle.setText(getIntent().getStringExtra("resipesTitle"));
        resipesDate.setText(getIntent().getStringExtra("resipesDate"));
        resipesLevel.setText(getIntent().getStringExtra("resipesLevel"));
        resipesText.setText(getIntent().getStringExtra("resipesText"));
        resipesShortText.setText(getIntent().getStringExtra("recipeShortDescription"));
    }


    public void OpenRecipes(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void OpelPersonalAcc(View view){
        Intent intent = new Intent(this, Personal_acc.class);
        startActivity(intent);
    }

    public void Request(View view){
        Intent intent = new Intent(this, Request.class);
        startActivity(intent);
    }

    public void AddResipesBd(View view){
        mdatabaseref = FirebaseDatabase.getInstance().getReference("Cook");
        mdatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()){
                    Cook value = ds.getValue(Cook.class);


                }
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
    }

    public void DeleteResipesBd(View view){
        Intent intent = new Intent(this, Request.class);
        startActivity(intent);
    }
}