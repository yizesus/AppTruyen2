package com.example.apptruyen;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Category extends MainActivity {
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        addControls();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(Category.this, MainActivity.class);
            }
        });
    }

    private void addControls() {
        back = findViewById(R.id.back);
    }

    @Override
    public void onBackPressed() {
        redirectActivity(Category.this, MainActivity.class);
        super.onBackPressed();
    }
}