package com.example.cookbookfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cookbookfinal.Models.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Setting extends AppCompatActivity {
    public FirebaseAuth mAuth;
    public DatabaseReference myRef;
    DatabaseReference mdatabaseref;
    User user;
    ConstraintLayout root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


    }

    public void DeleteSetting(View view){
        Intent intent = new Intent(this, Personal_acc.class);
        startActivity(intent);
    }

    public void AddResipesBd(View view){

        final MaterialEditText username = findViewById(R.id.UsernameSetting);
        final MaterialEditText password = findViewById(R.id.NewPasswordSettings);

        myRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser UseruID = mAuth.getInstance().getCurrentUser();

        root = findViewById(R.id.rootSetting);

        if (password.getText().toString().length() < 5){
            Snackbar.make(root, "Введите пароль более 5 символов", Snackbar.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(username.getText().toString())){
            Snackbar.make(root, "Введите ваш ник", Snackbar.LENGTH_SHORT).show();
            return;
        }

        mdatabaseref = FirebaseDatabase.getInstance().getReference("Users");
        mdatabaseref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()){
                    User value = ds.getValue(User.class);
                    Boolean role = value.getRole();

                    String tt = UseruID.getUid();

                    if (ds.getKey().toString().equals(tt)) {
                        DatabaseReference settingName = ds.getRef().child("name");
                        settingName.setValue(username.getText().toString());

                        DatabaseReference settingPassword = ds.getRef().child("password");
                        settingPassword.setValue(password.getText().toString());

                        Intent intent = new Intent(Setting.this, Personal_acc.class);
                        startActivity(intent);
                    };
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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