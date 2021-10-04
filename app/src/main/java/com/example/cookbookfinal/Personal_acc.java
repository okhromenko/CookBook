package com.example.cookbookfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Personal_acc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_acc);
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