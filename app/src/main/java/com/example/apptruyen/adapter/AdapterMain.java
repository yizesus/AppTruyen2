package com.example.apptruyen.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apptruyen.CRUD;
import com.example.apptruyen.DetailTruyen;
import com.example.apptruyen.LOGIN;
import com.example.apptruyen.MainActivity;
import com.example.apptruyen.R;
import com.example.apptruyen.model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdapterMain extends FirebaseRecyclerAdapter<model,AdapterMain.myviewholder> {
    Context context;

    public AdapterMain(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    protected void onBindViewHolder(@NonNull AdapterMain.myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final model model) {
        holder.name.setText(model.getName());
        holder.course.setText(model.getCourse());
        holder.email.setText(model.getEmail());
        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);




    }
    @NonNull
    @Override
    public AdapterMain.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from((parent.getContext())).inflate(R.layout.layout_truyen,parent,false);
        return  new AdapterMain.myviewholder(view);
    }

//    @NonNull
//    @Override
//    public AdapterMain.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from((parent.getContext())).inflate(R.layout.layout_truyen,parent,false);
//        return  new AdapterMain.myviewholder(view);
//    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img;
        ImageView edit,delete;
        TextView name,course,email;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.nametext);
            course=(TextView)itemView.findViewById(R.id.coursetext);
            email=(TextView)itemView.findViewById(R.id.emailtext);

            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}
