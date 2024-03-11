package com.example.apptruyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {
    TextView IfLogIn, Back;
    Button signup_but;
    EditText passID, nameID, emailID;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        addControls();
        signup_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String username = nameID.getText().toString().trim();
                String password = passID.getText().toString().trim();
                String Email = emailID.getText().toString().trim();
                Intent intent = new Intent(signup.this, LOGIN.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                intent.putExtra("email", Email);
                startActivity(intent);

                mAuth.createUserWithEmailAndPassword(Email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    Toast.makeText(getApplicationContext(), "Create successful", Toast.LENGTH_SHORT).show();
                                } else {

                                    Toast.makeText(signup.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                /////

                /////

            }
        });
        IfLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, LOGIN.class);
                startActivity(intent);
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        progressBar = findViewById(R.id.progressBar);
        Back = findViewById(R.id.tv_back);
        IfLogIn = findViewById(R.id.IfLogIn);
        signup_but = findViewById(R.id.signup_but);
        passID = findViewById(R.id.passID);
//        nameID = findViewById(R.id.nameID);
        emailID = findViewById(R.id.emailID);
    }
}