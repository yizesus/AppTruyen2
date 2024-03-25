package com.example.apptruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailTruyen extends AppCompatActivity {
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    private String mParam1;
//    private String mParam2;
//    String name, course, purl;
//    public DetailTruyen() {
//
//    }
//    public DetailTruyen(String name, String course, String purl) {
//        this.name=name;
//        this.course=course;
//
//        this.purl=purl;
//    }
//    public static DetailTruyen newInstance(String param1, String param2) {
//        DetailTruyen fragment = new DetailTruyen();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//
//        return fragment;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_truyen);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View view=inflater.inflate(R.layout.activity_detail_truyen, container, false);
//
//        ImageView imageholder=view.findViewById(R.id.imagegholder);
//        TextView nameholder=view.findViewById(R.id.nameholder);
//        TextView courseholder=view.findViewById(R.id.courseholder);
////        TextView emailholder=view.findViewById(R.id.emailholder);
//
//        nameholder.setText(name);
//        courseholder.setText(course);
////        emailholder.setText(email);
//        Glide.with(getContext()).load(purl).into(imageholder);
//
//
//        return  view;
//    }
//    public void onBackPressed()
//    {
//        AppCompatActivity activity=(AppCompatActivity)getContext();
//        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new rec_detail()).addToBackStack(null).commit();
//
//    }
}