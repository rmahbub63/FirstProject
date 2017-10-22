package com.example.mahbub.firstproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.mahbub.firstproject.Model.Student;
import com.example.mahbub.firstproject.adapter.CustomAdapter;
import com.example.mahbub.firstproject.database.DatabaseHelper;

import java.util.ArrayList;

public class AllProfileList extends AppCompatActivity {
    ListView listViewAllStudents;
    ArrayList<Student> arrayListStudent;
    ArrayAdapter<Student> arrayAdapterStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_profile_list);

        listViewAllStudents = (ListView) findViewById(R.id.listViewAllProfile);
        arrayListStudent = new ArrayList<Student>();

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        DatabaseHelper databaseHelper = new DatabaseHelper(AllProfileList.this);
        arrayListStudent = databaseHelper.getAllStudent();

        for(int i = 0; i < arrayListStudent.size(); i++){
            if(arrayListStudent.get(i).getEmail().equals(email)){
                arrayListStudent.remove(arrayListStudent.get(i));
                break;
            }
        }

        CustomAdapter customAdapter = new CustomAdapter(AllProfileList.this, arrayListStudent);

        arrayAdapterStudent = new ArrayAdapter<Student>(AllProfileList.this, R.layout.custom_adapter_layout, arrayListStudent);
//        listViewAllStudents.setAdapter(arrayAdapterStudent);
        listViewAllStudents.setAdapter(customAdapter);
        arrayAdapterStudent.notifyDataSetChanged();
    }
}
