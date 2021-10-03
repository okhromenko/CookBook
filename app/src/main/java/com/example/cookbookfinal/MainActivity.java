package com.example.cookbookfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbookfinal.adapter.CategoryAdapter;
import com.example.cookbookfinal.model.category;
import java.util.List;

import android.icu.util.ULocale;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycle;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<category> categoryList = new ArrayList<>();
        categoryList.add(new category(1, "Мясо"));
        categoryList.add(new category(2, "Торты"));
        categoryList.add(new category(3, "Супы"));
        categoryList.add(new category(4, "Прочее"));

        serCategoryRecycler(categoryList);
    }

    private void serCategoryRecycler(List<category> categoryList){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycle = findViewById(R.id.categoryRecycler);
        categoryRecycle.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycle.setAdapter(categoryAdapter);
    }
}