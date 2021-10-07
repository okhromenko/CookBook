package com.example.cookbookfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cookbookfinal.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
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
    private static final String TAG = "myLogs";


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
        final MaterialEditText email = findViewById(R.id.EmailSettings);
        final MaterialEditText password = findViewById(R.id.NewPasswordSettings);
        final MaterialEditText passwordOld = findViewById(R.id.OldPasswordSettings);

        myRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser UseruID = mAuth.getInstance().getCurrentUser();

        root = findViewById(R.id.rootSetting);

        if (passwordOld.getText().toString().length() < 5){
            Snackbar.make(root, "Введите пароль более 5 символов", Snackbar.LENGTH_SHORT).show();
            return;
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        AuthCredential credential = EmailAuthProvider
                .getCredential(email.getText().toString(), passwordOld.getText().toString());


        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(password.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "Password updated");
                                        startActivity(new Intent(Setting.this, Request.class));
                                        finish();
                                    } else {
                                        Log.d(TAG, "Error password not updated");
                                        startActivity(new Intent(Setting.this, Request.class));
                                        finish();
                                    }
                                }
                            });
                        } else {
                            Log.d(TAG, "Error auth failed");
                            startActivity(new Intent(Setting.this, Request.class));
                            finish();
                        }
                    }
                });

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