package com.example.quera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quera.Models.Answer;
import com.example.quera.Models.Classroom;

import java.util.ArrayList;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder> {
    private ArrayList<Answer> answers = new ArrayList<>();
    private Context context;

    public AnswerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_list_item, parent, false);
        AnswerAdapter.AnswerViewHolder holder = new AnswerAdapter.AnswerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.studentName.setText(answers.get(position).getStudent().getName());
        holder.studentScore.setText(answers.get(position).getScore());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AnswerScoreActivity.class);
                intent.putExtra("StudentName", answers.get(position).getStudent().getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public void setStudentAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
        notifyDataSetChanged();
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder {
        private TextView studentName;
        private TextView studentScore;
        private CardView parent;
        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.textStudentOfTheClassName);
            studentScore = itemView.findViewById(R.id.textStudentOfTheClassScore);
            parent = itemView.findViewById(R.id.answerItemCardView);
        }
    }
}
