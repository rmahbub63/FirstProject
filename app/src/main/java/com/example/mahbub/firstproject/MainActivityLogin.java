package com.example.mahbub.firstproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahbub.firstproject.Model.Student;
import com.example.mahbub.firstproject.database.DatabaseHelper;

public class MainActivityLogin extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        editTextEmail = (EditText) findViewById(R.id.editTextLoginEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextLoginPass);

        editTextEmail.setText("rmahbub63@gmail.com");
        editTextPassword.setText("123456789");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.exit(0);
    }

    public void loginClickAction(View view) {

        if(formValidation()){
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString();

            DatabaseHelper databaseHelper = new DatabaseHelper(MainActivityLogin.this);

            String receivedPass = databaseHelper.getPassByMail(email);

            if(receivedPass == null){
                Toast.makeText(MainActivityLogin.this, "Your Email is not Registered", Toast.LENGTH_SHORT).show();
            } else {
                if(receivedPass.equals(password)){
                    Intent intent = new Intent(MainActivityLogin.this, ProfileView.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivityLogin.this, "Password do not match", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void newAccountClickAction(View view) {
        Intent intent = new Intent(MainActivityLogin.this, Registration.class);
        startActivity(intent);
    }

    public boolean formValidation(){
        boolean value = false;
        if(isEmailValid() && isPasswordValid()){
            value = true;
        }
//        Toast.makeText(MainActivity.this, "Return value in formValidation " + value, Toast.LENGTH_LONG).show();
        return value;
    }

    public boolean isEmailValid(){

        String email = editTextEmail.getText().toString().trim();

        String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Boolean b = email.matches(EMAIL_REGEX);

        if(email.isEmpty()){
            editTextEmail.setError("Please insert email");
            return false;
        } else if(!b){
            editTextEmail.setError("Please insert valid email");
            return false;
        }
        return true;
    }

    public boolean isPasswordValid(){
        String password = editTextPassword.getText().toString();
        if(password.isEmpty()){
            editTextPassword.setError("Please insert password");
            return false;
        } else if(password.length() < 8){
            editTextPassword.setError("Please insert at least 8 characters");
            return false;
        }

        return true;
    }

}
