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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    //Declaration
    Button btnReset, btnBack;
    EditText edtEmail;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    String strEmail;
    TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        addControls();



        mAuth = FirebaseAuth.getInstance();

        //Reset Button Listener
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strEmail = edtEmail.getText().toString().trim();
                if (!TextUtils.isEmpty(strEmail)) {
                    ResetPassword();
                } else {
                    edtEmail.setError("Email field can't be empty");
                }
            }

            private void ResetPassword() {
                progressBar.setVisibility(View.VISIBLE);
                btnReset.setVisibility(View.INVISIBLE);

                mAuth.sendPasswordResetEmail(strEmail)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(ForgotPassword.this, "Reset Password link has been sent to your registered Email", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ForgotPassword.this, LOGIN.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ForgotPassword.this, "Error :- " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                btnReset.setVisibility(View.VISIBLE);
                            }
                        });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        redirectActivity(ForgotPassword.this, MainActivity.class);
        super.onBackPressed();
    }
    private void addControls() {
        back = findViewById(R.id.tv_back);
        btnReset = findViewById(R.id.btn_forgot_pass);
        edtEmail = findViewById(R.id.emailID);
        progressBar = findViewById(R.id.progressBar);
    }
}