package com.example.apptruyen;

import static com.example.apptruyen.MainActivity.redirectActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LOGIN extends AppCompatActivity {
    EditText passID, nameID;
    Button signinBUT;
    TextView IfLogIn2, Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        signinBUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String username = nameID.getText().toString().trim();
                //String password = passID.getText().toString().trim();
                Intent intent = new Intent(LOGIN.this, MainActivity.class);
                // intent.putExtra("username",username);
                //intent.putExtra("password",password);
                startActivity(intent);
            }
        });
        IfLogIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LOGIN.this, signup.class);
                startActivity(intent);
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LOGIN.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        Back = findViewById(R.id.tv_back);
        IfLogIn2 = findViewById(R.id.IfLogIn2);
        passID = findViewById(R.id.passID);
        nameID = findViewById(R.id.nameID);
        signinBUT = findViewById(R.id.signinBUT);
    }

    @Override
    public void onBackPressed() {
        redirectActivity(LOGIN.this, MainActivity.class);
        super.onBackPressed();
    }
}