package com.example.cookbookfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cookbookfinal.Models.Cook;
import com.example.cookbookfinal.adapter.CategoryAdapter;
import com.example.cookbookfinal.adapter.RecipesAdapter;
import com.example.cookbookfinal.adapter.RequestAdapter;

import java.util.ArrayList;
import java.util.List;

public class Request extends AppCompatActivity {

    static List<Cook> requestList = new ArrayList<>();


    RecyclerView requestRecycle;
    static RequestAdapter requestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        requestList.add(new Cook("Безе", "Вкуснейший десерт!",
                "Каждый пользователь, лишь раз увидев безе, захочет её приготовить!",
                "python_3", "1 час", "средний", true,  "Десерт"));
        requestList.add(new Cook("Шоколадные кексы", "Вкуснейший десерт!",
                "Каждый пользователь, лишь раз увидев безе, захочет её приготовить!",
                "python_3", "1 час", "средний", true,  "Десерт"));
        requestList.add(new Cook("Мармелад", "Вкуснейший десерт!",
                "Каждый пользователь, лишь раз увидев безе, захочет её приготовить!",
                "python_3", "1 час", "средний", true,  "Десерт"));

        setRequestRecycler(requestList);

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