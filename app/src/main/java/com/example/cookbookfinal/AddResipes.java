package com.example.cookbookfinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;


import com.example.cookbookfinal.Models.Cook;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class AddResipes extends AppCompatActivity {
    Button btnAddRecipes, btnDeleteResipes;
    ConstraintLayout add_resipes_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resipes);

        btnAddRecipes = findViewById(R.id.SaveButton);
        btnDeleteResipes = findViewById(R.id.DeleteButton);

        add_resipes_root = findViewById(R.id.add_resipes_root);

        btnAddRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final MaterialEditText Title = findViewById(R.id.AddRedipesTitle);
                final MaterialEditText Level = findViewById(R.id.AddRedipesLevel);
                final MaterialEditText AddImage = findViewById(R.id.AddRedipesImage);
                final MaterialEditText Time = findViewById(R.id.AddRedipesTime);
                final MaterialEditText ShortDescription = findViewById(R.id.AddRedipesShortDescription);
                final MaterialEditText Description = findViewById(R.id.AddRedipesDescription);
                final MaterialEditText AddCategory = findViewById(R.id.AddRedipesCategory);

                if (TextUtils.isEmpty(Title.getText().toString())){
                    Snackbar.make(add_resipes_root, "Введите название рецепта", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Level.getText().toString())){
                    Snackbar.make(add_resipes_root, "Оцените сложность рецепта", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Time.getText().toString())){
                    Snackbar.make(add_resipes_root, "Введите время приготовления", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(ShortDescription.getText().toString())){
                    Snackbar.make(add_resipes_root, "Введите краткое описание рецепта", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Description.getText().toString())){
                    Snackbar.make(add_resipes_root, "Введите рецепт", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                Cook cook = new Cook(Title.getText().toString(), ShortDescription.getText().toString(),
                        Description.getText().toString(), AddImage.getText().toString(), Time.getText().toString(),
                        Level.getText().toString(), true, AddCategory.getText().toString());
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Cook").push().setValue(cook, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Snackbar.make(add_resipes_root, "Рецепт добавлен",
                                Snackbar.LENGTH_SHORT).show();
                    }
                });
                startActivity(new Intent(AddResipes.this, MainActivity.class));
                finish();
            }
        });

        btnDeleteResipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddResipes.this, MainActivity.class));
                finish();
            }
        });

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
}