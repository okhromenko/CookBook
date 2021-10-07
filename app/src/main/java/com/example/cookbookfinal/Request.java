package com.example.cookbookfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cookbookfinal.Models.Category;
import com.example.cookbookfinal.Models.Cook;
import com.example.cookbookfinal.Models.User;
import com.example.cookbookfinal.adapter.CategoryAdapter;
import com.example.cookbookfinal.adapter.RecipesAdapter;
import com.example.cookbookfinal.adapter.RequestAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Request extends AppCompatActivity {

    static List<Cook> requestList = new ArrayList<>();


    RecyclerView requestRecycle;
    static RequestAdapter requestAdapter;
    public FirebaseAuth mAuth;
    public DatabaseReference myRef;
    DatabaseReference mdatabaseref;
    List <Cook> listCook = new ArrayList<>();
    static List<Cook> recipesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        mdatabaseref = FirebaseDatabase.getInstance().getReference("Cook");
        mdatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()){
                    Cook value = ds.getValue(Cook.class);
                    if (value.getRelease() == false){listCook.add(value);}
                }
                recipesList.clear();
                recipesList.addAll(listCook);
                setRequestRecycler(recipesList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


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

    }

    private void setRequestRecycler(List<Cook> requestList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        requestRecycle = findViewById(R.id.RequestRecycle);
        requestRecycle.setLayoutManager(layoutManager);

        requestAdapter = new RequestAdapter(this, requestList);
        requestRecycle.setAdapter(requestAdapter);
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