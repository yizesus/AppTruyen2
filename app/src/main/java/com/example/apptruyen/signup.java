package com.example.apptruyen;

import static com.example.apptruyen.MainActivity.redirectActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {
    TextView Back;
    Button signup_but;
    EditText passID, emailID;
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;
    ProgressBar progressBar;
    CheckBox checkBoxAdmin, checkBoxUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        addControls();

        signup_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = passID.getText().toString();
                String Email = emailID.getText().toString();
                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(signup.this, "nhập email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(signup.this, "nhập password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!checkBoxAdmin.isChecked() && !checkBoxUser.isChecked()){
                    Toast.makeText(signup.this, "vui lòng chọn User hoặc Admin", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(Email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    DocumentReference df = mStore.collection("Users").document(user.getUid());
                                    Map<String,Object> userInfo=new HashMap<>();
                                    userInfo.put("email",emailID.getText().toString());
                                    userInfo.put("password",passID.getText().toString());
                                    if(checkBoxUser.isChecked()){
                                        userInfo.put("isUser", "1");
                                    }
                                    if(checkBoxAdmin.isChecked()){
                                        userInfo.put("isAdmin", "1");
                                    }

                                    df.set(userInfo);
                                    // Sign in success, update UI with the signed-in user's information

                                    Intent intent = new Intent(getApplicationContext(),LOGIN.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(signup.this, "Đăng ký thất bại! email hoặc mật khẩu không phù hợp",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


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
    @Override
    public void onBackPressed() {
        redirectActivity(signup.this, MainActivity.class);
        super.onBackPressed();
    }
    private void addControls() {
        progressBar = findViewById(R.id.progressBar);
        Back = findViewById(R.id.tv_back);
        signup_but = findViewById(R.id.btn_signup);
        passID = findViewById(R.id.passID);
        emailID = findViewById(R.id.emailID);
        checkBoxAdmin = findViewById(R.id.isAdmin);
        checkBoxUser = findViewById(R.id.isUser);
    }
}