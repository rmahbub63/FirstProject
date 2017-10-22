package com.example.mahbub.firstproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mahbub.firstproject.Model.Student;
import com.example.mahbub.firstproject.database.DatabaseHelper;

public class ProfileView extends AppCompatActivity {

    TextView textViewName, textViewPassword, textViewId, textViewEmail, textViewPhone, textViewGender, textViewCgpa;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        textViewName = (TextView) findViewById(R.id.textViewNameProfile);
        textViewPassword = (TextView) findViewById(R.id.textViewPassword);
        textViewId = (TextView) findViewById(R.id.textViewId);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewPhone = (TextView) findViewById(R.id.textViewPhone);
        textViewGender = (TextView) findViewById(R.id.textViewGender);
        textViewCgpa = (TextView) findViewById(R.id.textViewCgpa);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        DatabaseHelper databaseHelper = new DatabaseHelper(ProfileView.this);
        Student student = databaseHelper.getSingleStudentByEmail(email);

        textViewName.setText(student.getName());
        textViewPassword.setText(student.getPassword());
        textViewId.setText(student.getId());
        textViewEmail.setText(student.getEmail());
        textViewPhone.setText(student.getPhone());
        textViewGender.setText(student.getGender());
        textViewCgpa.setText(String.valueOf(student.getCgpa()));
    }

    public void exitClickAction(View view) {
        finish();
        System.exit(0);
    }

    public void viewOthersClickAction(View view) {
        Intent intent = new Intent(ProfileView.this, AllProfileList.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}
