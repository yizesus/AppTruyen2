package com.example.apptruyen;

import static com.example.apptruyen.MainActivity.redirectActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LOGIN extends AppCompatActivity {
    EditText passID, emailID;
    Button loginBUT;
    TextView Register;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    TextView forgotPassword;

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
                progressBar.setVisibility(View.VISIBLE);
                String password = passID.getText().toString().trim();
                String Email = emailID.getText().toString().trim();
                Intent intent = new Intent(LOGIN.this, LOGIN.class);
                intent.putExtra("password", password);
                intent.putExtra("email", Email);
                startActivity(intent);

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

                                    Toast.makeText(LOGIN.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    
                                }
                            }
                        });
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(LOGIN.this);
                View dialogView = getLayoutInflater().inflate(R.layout.activity_forgot_p,null);
                EditText emailBox = dialogView.findViewById(R.id.emailBox);

                builder.setView(dialogView);
                AlertDialog dialog=builder.create();
                dialog.findViewById(R.id.bntReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userEmail=emailBox.getText().toString();
                        if(TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                            Toast.makeText(LOGIN.this, "Enter your registered email", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(LOGIN.this, "Check your email", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                                else
                                {
                                    Toast.makeText(LOGIN.this, "Unable to send, failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialogView.findViewById(R.id.bntCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                if(dialog.getWindow()!=null)
                {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moForgotPass();
            }
        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), signup.class);
                startActivity(intent);
            }
        });
    }

    private void moForgotPass() {
        Intent intent= new Intent(LOGIN.this,ForgotP.class);
        startActivity(intent);
    }

    private void addControls() {
        progressBar=findViewById(R.id.progressBar);
        Register = findViewById(R.id.tv_register);
        passID = findViewById(R.id.passID);
        loginBUT = findViewById(R.id.loginBUT);
        mAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.emailID);
        forgotPassword=findViewById(R.id.Forgot_Password);
    }

    @Override
    public void onBackPressed() {
        redirectActivity(LOGIN.this, MainActivity.class);
        super.onBackPressed();
    }
}