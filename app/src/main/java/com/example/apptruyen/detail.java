package com.example.apptruyen;

import static com.example.apptruyen.MainActivity.redirectActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class detail extends AppCompatActivity {

    ImageView ivImg, back;
    TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        back = findViewById(R.id.back);
        tvName = findViewById(R.id.nametext);
        ivImg = findViewById(R.id.imageholder);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String purl = intent.getStringExtra("purl");

        tvName.setText(name);
        Glide.with(this)
                .load(purl)
                .error(R.drawable.baseline_error_24)
                .into(ivImg);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detail.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        redirectActivity(detail.this, MainActivity.class);
        super.onBackPressed();
    }
}