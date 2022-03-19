package com.example.quera;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quera.Models.Account;
import com.example.quera.Models.Classroom;
import com.example.quera.Models.Exercise;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private ArrayList<Exercise> exercises = new ArrayList<>();
    private Context context;
    public ExerciseAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_list_item, parent, false);
        ExerciseAdapter.ExerciseViewHolder holder = new ExerciseAdapter.ExerciseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        holder.exerciseName.setText(exercises.get(position).getName());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Account.loggedInAccount.getClass().getName().equals("com.example.quera.Models.Student")){
                    Intent studentExerciseIntent = new Intent(context, ExercisePageActivity.class);
                    context.startActivity(studentExerciseIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void setExercisesClasses(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private TextView exerciseName;
        private CardView parent;
        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.textStudentExerciseName);
            parent = itemView.findViewById(R.id.exerciseCardView);
        }
    }
}
