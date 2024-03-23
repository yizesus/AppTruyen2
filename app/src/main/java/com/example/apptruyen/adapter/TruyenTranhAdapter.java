package com.example.apptruyen.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apptruyen.R;
///////////////////////////////////////////////////////////////////////////////
import com.example.apptruyen.model;
////////////////////////////////////////////////////////////////////////////////
import com.example.apptruyen.object.TruyenTranh;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter_LifecycleAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class TruyenTranhAdapter extends FirebaseRecyclerAdapter<model,TruyenTranhAdapter.myviewholder> {
    public TruyenTranhAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final model model)
    {
        holder.name.setText(model.getName());
        holder.course.setText(model.getCourse());
        holder.email.setText(model.getEmail());
        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1100)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText purl=myview.findViewById(R.id.uimgurl);
                final EditText name=myview.findViewById(R.id.uname);
                final EditText course=myview.findViewById(R.id.ucourse);
                final EditText email=myview.findViewById(R.id.uemail);
                Button submit=myview.findViewById(R.id.usubmit);

                purl.setText(model.getPurl());
                name.setText(model.getName());
                course.setText(model.getCourse());
                email.setText(model.getEmail());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("purl",purl.getText().toString());
                        map.put("name",name.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("course",course.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("students")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("students")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });



    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from((parent.getContext())).inflate(R.layout.singlerow,parent,false);
        return  new myviewholder(view);
    }
//    private Context ct;
//    private ArrayList<TruyenTranh> arr;
//
//    public TruyenTranhAdapter(@NonNull Context context, int resource, @NonNull List<TruyenTranh> objects) {
//        super(context, resource, objects);
//        this.ct = context;
//        this.arr = new ArrayList<>(objects);
//    }
//
//    public void sortTruyen(String s) {
//        s = s.toUpperCase();
//        int k = 0;
//        for (int i = 0; i < arr.size(); i++) {
//            TruyenTranh t = arr.get(i);
//            String ten = t.getTenTruyen().toUpperCase();
//            if (ten.indexOf(s) >= 0) {
//                arr.set(i, arr.get(k));
//                arr.set(k, t);
//                k++;
//            }
//        }
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        if (convertView == null) {
//            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.item_truyen, null);
//        }
//        if (arr.size() > 0) {
//            TruyenTranh truyenTranh = this.arr.get(position);
//            TextView tenTenTruyen = convertView.findViewById(R.id.tenTruyen);
//            TextView tenSoChap = convertView.findViewById(R.id.soChap);
//            ImageView imgAnhTruyen = convertView.findViewById(R.id.imgAnhTruyen);
//
//            tenTenTruyen.setText(truyenTranh.getTenTruyen());
//            tenSoChap.setText(truyenTranh.getTenChap());
//            Glide.with(this.ct).load(truyenTranh.getLinkAnh()).into(imgAnhTruyen);
//        }
//        return convertView;
//    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        ImageView edit,delete;
        TextView name,course,email;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(CircleImageView) itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.nametext);
            course=(TextView)itemView.findViewById(R.id.coursetext);
            email=(TextView)itemView.findViewById(R.id.emailtext);

            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}
