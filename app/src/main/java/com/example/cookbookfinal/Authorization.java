package com.example.cookbookfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.cookbookfinal.Models.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Authorization extends AppCompatActivity {
    Button btnSignIn;
    ImageView btnRegister;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        btnSignIn = findViewById(R.id.buttonAuthorization);
        btnRegister = findViewById(R.id.buttonRegister);

        root = findViewById(R.id.root_element);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterWindow();
            }
        });
    }

    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Введите все данные для регистрации");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.register_window, null);
        dialog.setView(register_window);

        final MaterialEditText email = register_window.findViewById(R.id.emailField);
        final MaterialEditText name = register_window.findViewById(R.id.nameField);
        final MaterialEditText password = register_window.findViewById(R.id.passField);
        final MaterialEditText password_double = register_window.findViewById(R.id.passField_double);



        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

            dialog.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (password != password_double){
                        Snackbar.make(root, "Пароли не совпадают", Snackbar.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(email.getText().toString())){
                        Snackbar.make(root, "Введите вашу почту", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(name.getText().toString())){
                        Snackbar.make(root, "Введите ваш ник", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if (password.getText().toString().length() < 5){
                        Snackbar.make(root, "Введите пароль более 5 символов", Snackbar.LENGTH_SHORT).show();
                        return;
                    }

                    // Регистрация пользователя
                    auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    User user = new User();
                                    user.setEmail(email.getText().toString());
                                    user.setName(name.getText().toString());
                                    user.setPassword(password.getText().toString());
                                    user.setRole(false);

                                    users.child(user.getEmail()).setValue(user)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Snackbar.make(root, "Пользователь добавлен", Snackbar.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            });
                }
            });
            dialog.show();
    }
}