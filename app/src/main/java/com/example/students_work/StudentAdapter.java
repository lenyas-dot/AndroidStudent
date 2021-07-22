package com.example.students_work;

import android.graphics.Movie;
import android.net.sip.SipSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {

    private List<Student> students;
    private Listener onStudentClickListener;

    public StudentAdapter(List<Student> students, Listener onStudentClickListener)
    {
        this.students = students;
        this.onStudentClickListener = onStudentClickListener;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_view, parent, false);
        view.setOnClickListener(v -> onStudentClickListener.onStudentClick((Student) v.getTag()));
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Student student = students.get(position);
        holder.itemView.setTag(student);
        holder.bind(student);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class StudentHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        private TextView textView;

        private StudentHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.im_adapter);
            textView = itemView.findViewById(R.id.tv_adapter);
        }

        private void bind(@NonNull Student student)
        {
            imageView.setImageResource(student.getPhoto());
            textView.setText(student.getFirstName() + " " + student.getSecondName());
        }
    }
    interface Listener
    {
        void onStudentClick(Student Student);
    }
}
