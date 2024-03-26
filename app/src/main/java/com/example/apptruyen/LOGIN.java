package com.example.apptruyen;

import static com.example.apptruyen.MainActivity.redirectActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LOGIN extends AppCompatActivity {
    EditText passID, emailID;
    Button loginBUT;
    TextView Register, forgotPassword;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

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
        setContentView(R.layout.activity_login);
        addControls();
        loginBUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passID.getText().toString().trim();
                String Email = emailID.getText().toString().trim();
                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(LOGIN.this, "nhập email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(LOGIN.this, "nhập password", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                /////
                mAuth.signInWithEmailAndPassword(Email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    finish();
    
                                } else {

                                    Toast.makeText(LOGIN.this, "Đăng nhập không hợp lệ, vui lòng kiểm tra lại",
                                            Toast.LENGTH_SHORT).show();
                                    
                                }
                            }
                        });
            }
        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), signup.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });
    }


    private void addControls() {
        forgotPassword = findViewById(R.id.Forgot_Password);
        progressBar=findViewById(R.id.progressBar);
        Register = findViewById(R.id.tv_register);
        passID = findViewById(R.id.passID);
        loginBUT = findViewById(R.id.loginBUT);
        mAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.emailID);
    }

    @Override
    public void onBackPressed() {
        redirectActivity(LOGIN.this, MainActivity.class);
        super.onBackPressed();
    }
}