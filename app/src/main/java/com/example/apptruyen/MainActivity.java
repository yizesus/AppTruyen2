package com.example.apptruyen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyen.adapter.AdapterMain;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    AdapterMain adapterMain;
    RecyclerView RecMain;
    FirebaseAuth auth;
    FirebaseUser user;
    DrawerLayout drawer_layout;
    LinearLayout TheLoaiID, homeID, crudID;
    ImageView menu;

    Button DangXuat;
    SearchView edtTimKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        anhXa();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        RecMain = (RecyclerView) findViewById(R.id.RecMain);
        RecMain.setLayoutManager(new GridLayoutManager(this, 2));


        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students"), model.class)
                        .build();
        /////////
        adapterMain = new AdapterMain(options);
        RecMain.setAdapter(adapterMain);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////


        edtTimKiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return true;
            }
        });


        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), LOGIN.class);
            startActivity(intent);
            finish();
        }
        DangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LOGIN.class);
                startActivity(intent);
                finish();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawer_layout);
            }
        });
        homeID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                finish();
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });
        TheLoaiID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainActivity.this, Category.class);
            }
        });

        crudID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainActivity.this, CRUD.class);
            }
        });
    }

    private void processSearch(String s) {
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students").orderByChild("name").startAt(s).endAt(s + "\uf8ff"), model.class)
                        .build();
        adapterMain.updateOptions(options);
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapterMain.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterMain.stopListening();
    }


    private void addControls() {
        drawer_layout = findViewById(R.id.drawer_layout);
        menu = findViewById(R.id.menu);
        homeID = findViewById(R.id.homeID);
        TheLoaiID = findViewById(R.id.TheLoaiID);
        DangXuat = findViewById(R.id.userLogOut);
        crudID = findViewById(R.id.tv_CRUD);


    }

    public static void openDrawer(DrawerLayout drawer_layout) {
        drawer_layout.openDrawer(GravityCompat.START);

    }

    public static void closeDrawer(DrawerLayout drawer_layout) {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) ;
        drawer_layout.closeDrawer(GravityCompat.START);
    }

    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawer_layout);
    }


    private void anhXa() {
        edtTimKiem = (SearchView) findViewById(R.id.edtTimKiem);
    }

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}