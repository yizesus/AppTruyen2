package com.example.apptruyen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.apptruyen.adapter.TruyenTranhAdapter;
import com.example.apptruyen.object.TruyenTranh;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    DrawerLayout drawer_layout;
    LinearLayout DangNhapID , TheLoaiID, homeID;
    ImageView menu;

    GridView gdvDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;
    Button emailtv;
    EditText edtTimKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        emailtv = findViewById(R.id.userLogOut);
        user = auth.getCurrentUser();

        addControls();

        init();
        anhXa();
        setUp();
        setClick();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), LOGIN.class);
            startActivity(intent);
            finish();
        }
        emailtv.setOnClickListener(new View.OnClickListener() {
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
        DangNhapID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainActivity.this, LOGIN.class);
            }
        });

        gdvDSTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Intent intent = new Intent(getApplicationContext(), ItemClickedActivity.class);
                intent.putExtra("id",pos);
                startActivity(intent);
            }
        });
    }


    private void addControls() {
        drawer_layout = findViewById(R.id.drawer_layout);
        menu = findViewById(R.id.menu);
        DangNhapID = findViewById(R.id.DangNhapID);
        homeID = findViewById(R.id.homeID);
        TheLoaiID = findViewById(R.id.TheLoaiID);
        emailtv = findViewById(R.id.userLogOut);


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

    private void init() {
        truyenTranhArrayList = new ArrayList<>();
        truyenTranhArrayList.add(new TruyenTranh("Forward", "Chap 2", "https://www.nettruyentr.vn/images/comics/forward.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Cao Võ: Hạ Cánh Đến Một Vạn Năm Sau", "Chap 88", "https://www.nettruyentr.vn/images/comics/cao-vo-ha-canh-den-mot-van-nam-sau.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Cặp Đôi Hướng Nội", "Chap 27", "https://www.nettruyentr.vn/images/comics/cap-doi-huong-noi.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("The Gamer", "Chap 418", "https://www.nettruyentr.vn/images/comics/the-gamer.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Thám Tử Conan", "Chap 1125", "https://st.nettruyenbb.com/data/comics/30/tham-tu-conan-7281.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Tôi Sẽ Thoát Khỏi Đóa Hoa Của Thử Thách", "Chap 3", "https://www.nettruyentr.vn/images/comics/toi-se-thoat-khoi-doa-hoa-cua-thu-thach.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Nhà Tôi Có Một Con Chuột", "Chap 27", "https://www.nettruyentr.vn/images/comics/nha-toi-co-mot-con-chuot.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Không Chỉ Là Bắt Nạt", "Chap 132", "https://www.nettruyentr.vn/images/comics/khong-chi-la-bat-nat.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Hắc Kị Sĩ Thiên Tài Giới Hạn Thời Gian", "Chap 47", "https://www.nettruyentr.vn/images/comics/hac-ki-si-thien-tai-gioi-han-thoi-gian.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Người Chơi Mới Cấp Tối Đa", "Chap 147", "https://www.nettruyentr.vn/images/comics/nguoi-choi-moi-cap-toi-da.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Khởi Đầu Có Kiếm Vực, Ta Sẽ Trở Thành Kiếm Thần", "Chap 149", "https://www.nettruyentr.vn/images/comics/khoi-dau-co-kiem-vuc-ta-se-tro-thanh-kiem-than.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Ác Qủy Trở Lại Học Đường", "Chap 53", "https://www.nettruyentr.vn/images/comics/ac-quy-tro-lai-hoc-duong.jpg"));

        adapter = new TruyenTranhAdapter(this, 0, truyenTranhArrayList);

    }

    private void anhXa() {
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
        edtTimKiem = findViewById(R.id.edtTimKiem);
    }

    private void setUp() {
        gdvDSTruyen.setAdapter(adapter);
    }

    private void setClick() {
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtTimKiem.getText().toString();
                adapter.sortTruyen(s);
            }
        });
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