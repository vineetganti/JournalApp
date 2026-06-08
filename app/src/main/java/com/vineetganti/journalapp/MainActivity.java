package com.vineetganti.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button loginBtn, createAccountBtn;
    private EditText emailEt, passwordEt;

    // Firebase Authentication
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createAccountBtn = findViewById(R.id.creat_account);
        createAccountBtn.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(i);
        });

        // Login
        loginBtn = findViewById(R.id.email_signin);
        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password);

        // Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance();
        loginBtn.setOnClickListener(v -> {
            logEmailPassUser(
                    emailEt.getText().toString().trim(),
                    passwordEt.getText().toString().trim()
            );
        }
        );

    }

    private void logEmailPassUser(
            String email,
            String pwd
    ){
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pwd)
        ){
            firebaseAuth.signInWithEmailAndPassword(email, pwd)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            Intent i = new Intent(MainActivity.this, JournalListActivity.class);
                            startActivity(i);
                            }
                        });

                    }
        }
    }