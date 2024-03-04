package com.example.apptruyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {
    TextView IfLogIn, Back;
    Button signup_but;
    EditText passID, nameID, emailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        addControls();
        signup_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = nameID.getText().toString().trim();
                String password = passID.getText().toString().trim();
                String Email = emailID.getText().toString().trim();
                Intent intent = new Intent(signup.this, LOGIN.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                intent.putExtra("email", Email);
                startActivity(intent);
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
        Back = findViewById(R.id.tv_back);
        IfLogIn = findViewById(R.id.IfLogIn);
        signup_but = findViewById(R.id.signup_but);
        passID = findViewById(R.id.passID);
        nameID = findViewById(R.id.nameID);
        emailID = findViewById(R.id.emailID);
    }
}