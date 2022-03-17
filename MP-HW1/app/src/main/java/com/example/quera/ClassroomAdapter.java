package com.example.quera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quera.Models.Classroom;

import java.util.ArrayList;

public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.StudentViewHolder> {

    private ArrayList<Classroom> classrooms = new ArrayList<>();
    private Context context;

    public ClassroomAdapter(Context context) {
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
        holder.studentClassName.setText(classrooms.get(position).getClassName());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, classrooms.get(position).getClassName() + " Selected",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return classrooms.size();
    }

    public void setStudentClasses(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
        notifyDataSetChanged();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        private TextView studentClassName;
        private CardView parent;
        private ImageView classImage;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentClassName = itemView.findViewById(R.id.textStudentClassName);
            parent = itemView.findViewById(R.id.studentClassList);
            classImage = itemView.findViewById(R.id.classImage);
        }
    }
}
