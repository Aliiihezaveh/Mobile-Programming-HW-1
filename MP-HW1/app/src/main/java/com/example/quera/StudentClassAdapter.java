package com.example.quera;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentClassAdapter extends RecyclerView.Adapter<StudentClassAdapter.StudentViewHolder> {

    private ArrayList<StudentClass> studentClasses = new ArrayList<>();
    public StudentClassAdapter() {
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

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
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentClassName = itemView.findViewById(R.id.textStudentClassName);
        }
    }
}
