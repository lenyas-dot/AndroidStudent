package com.example.students_work;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Student> students;
    private EditText name;
    private EditText surname;
    StudentAdapter studentAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        students = generateList();
        name = findViewById(R.id.et_name);
        surname = findViewById(R.id.et_surname);
        studentAdapter = new StudentAdapter(students, this::onStudentClick);
        RecyclerView recyclerView = findViewById(R.id.rv_students);
        recyclerView.setAdapter(studentAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        saveSetUp();
        addSetUp();
        deleteSetUp();
    }


    private void saveSetUp()
    {
        Button button = findViewById(R.id.btn_save);
        button.setOnClickListener(v -> onSaveButtonClickListener());
    }

    private void addSetUp()
    {
        Button button = findViewById(R.id.btn_add);
        button.setOnClickListener(v -> onAddButtonClickListener());
    }


    private void deleteSetUp()
    {
        Button button = findViewById(R.id.btn_delete);
        button.setOnClickListener(v -> onDeleteButtonClickListener());
    }

    private void onDeleteButtonClickListener()
    {
        if (name.getText().toString().isEmpty() || surname.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Заполнены не все поля!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            for (int i = 0; i < students.size(); i++)
            {

                if (name.getText().toString().equals(students.get(i).getFirstName()) && surname.getText().toString().equals(students.get(i).getSecondName()))
                {
                    students.remove(i);
                }
            }
            Toast.makeText(this, "Удаление произошло успешно", Toast.LENGTH_SHORT).show();
        }
        studentAdapter.notifyDataSetChanged();
    }



    private void onAddButtonClickListener()
    {
        name.setText("");
        surname.setText("");

    }

    private void onSaveButtonClickListener()
    {
        String tempName = name.getText().toString();
        String tempSurname = surname.getText().toString();
        if (!tempName.isEmpty() && !tempSurname.isEmpty()) {
            students.add(new Student(name.getText().toString(), surname.getText().toString(), false, R.drawable.female_3));
        }
        else {
            Toast.makeText(this, "Заполнены не все поля!", Toast.LENGTH_SHORT).show();
        }
        studentAdapter.notifyDataSetChanged();
    }

    private void onStudentClick(Student student)
    {
        ImageView photo = findViewById(R.id.im_avatar);
        name.setText(student.getFirstName());
        surname.setText(student.getSecondName());
        photo.setImageResource(student.getPhoto());
    }

    private List<Student> generateList()
    {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Lazarenko", "Leonid", true, R.drawable.male_3));
        students.add(new Student("Petrov", "Alexey", true, R.drawable.female_3));
        students.add(new Student("Vladislav", "Gandoshkin", true, R.drawable.vlad));
        students.add(new Student("Sergey", "Andreev", true, R.drawable.andrey));
        return students;
    }


}
