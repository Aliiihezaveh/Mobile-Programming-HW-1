package com.example.quera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentClassAdapter extends RecyclerView.Adapter<StudentClassAdapter.StudentViewHolder> {

    private ArrayList<StudentClass> studentClasses = new ArrayList<>();
    private Context context;

    public StudentClassAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_list_item, parent, false);
        StudentViewHolder holder = new StudentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.studentClassName.setText(studentClasses.get(position).getClassName());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,studentClasses.get(position).getClassName() + " Selected",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentClasses.size();
    }

    public void setStudentClasses(ArrayList<StudentClass> studentClasses) {
        this.studentClasses = studentClasses;
        notifyDataSetChanged();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        private TextView studentClassName;
        private RelativeLayout parent;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentClassName = itemView.findViewById(R.id.textStudentClassName);
            parent = itemView.findViewById(R.id.studentClassList);
        }
    }
}
