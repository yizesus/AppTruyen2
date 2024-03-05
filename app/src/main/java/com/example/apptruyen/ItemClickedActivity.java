package com.example.apptruyen;

import static com.example.apptruyen.MainActivity.redirectActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ItemClickedActivity extends AppCompatActivity {
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_clicked);
        addControls();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ItemClickedActivity.this, MainActivity.class);
            }
        });
    }

    private void addControls() {
        back = findViewById(R.id.back);
    }

    @Override
    public void onBackPressed() {
        redirectActivity(ItemClickedActivity.this, MainActivity.class);
        super.onBackPressed();
    }
}