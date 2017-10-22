package com.example.mahbub.firstproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mahbub.firstproject.Model.Student;
import com.example.mahbub.firstproject.database.DatabaseHelper;

public class Registration extends AppCompatActivity {

    EditText editTextName, editTextPassword, editTextId, editTextEmail, editTextPhone, editTextCgpa;
    RadioGroup radioGroupGender;
    RadioButton radioButtonMale, radioButtonFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextCgpa = (EditText) findViewById(R.id.editTextCgpa);

        radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);
        radioButtonMale = (RadioButton) findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton) findViewById(R.id.radioButtonFemale);

        editTextName.setText("Mahbub");
        editTextPassword.setText("123456789");
        editTextId.setText("151154761");
        editTextEmail.setText("rmahbub63@gmail.com");
        editTextPhone.setText("01946871244");
        editTextCgpa.setText("3.91");
        radioButtonMale.setChecked(true);
    }


    public void saveData(View view) {
        if(formValidation()){
            Student student = new Student();
            student.setName(editTextName.getText().toString().trim());
            student.setPassword(editTextPassword.getText().toString());
            student.setId(editTextId.getText().toString().trim());
            student.setEmail(editTextEmail.getText().toString().trim());
            student.setPhone(editTextPhone.getText().toString());
            student.setGender(getCurrentGender());
            student.setCgpa(Double.valueOf(editTextCgpa.getText().toString()));

            DatabaseHelper databaseHelper = new DatabaseHelper(Registration.this);
            databaseHelper.registerNewStudent(student);
        }
    }

    public boolean formValidation(){
        boolean value = false;
        if(isUserNameValid() && isPasswordValid() && isIdValid() && isEmailValid() && isPhoneValid() && isGenderValid() && isCgpaValid()){
            value = true;
        }
//        Toast.makeText(MainActivity.this, "Return value in formValidation " + value, Toast.LENGTH_LONG).show();
        return value;
    }

    public String getCurrentGender(){
        String gender = null;
        if (radioButtonMale.isChecked()){
            gender = "Male";
        } else {
            gender = "Female";
        }
        return gender;
    }

    public boolean isUserNameValid(){
        String userName = editTextName.getText().toString().trim();
        if(userName.isEmpty()){
            editTextName.setError("Please insert username");
            return false;
        } else if(userName.length() < 6 || userName.length() > 50){
            editTextName.setError("Please insert valid username");
            return false;
        } else if (userName.contains(" ")){
            editTextName.setError("Username should not contain any space");
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

    public boolean isIdValid(){
        String id = editTextId.getText().toString().trim();
        if(id.isEmpty()){
            editTextId.setError("Please insert id");
            return false;
        } else if(id.length() < 8){
            editTextId.setError("Please insert at least 8 characters");
            return false;
        }
        return true;
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

    public boolean isPhoneValid(){
        String phone = editTextPhone.getText().toString().trim();
        if(phone.isEmpty()){
            editTextPhone.setError("Please insert phone number");
            return false;
        } else if(phone.length() == 11 || phone.length() == 14) {
            if (phone.startsWith("+88")) {
                phone = phone.replace("+88", "");
            }
            if (phone.startsWith("017") || phone.startsWith("019") || phone.startsWith("018") || phone.startsWith("015")) {
            } else {
                editTextPhone.setError("Please insert valid phone number");
                return false;
            }
        } else {
            editTextPhone.setError("Please insert valid phone number");
            return false;
        }
        return true;
    }

    public boolean isGenderValid(){
        if(!radioButtonMale.isChecked() && !radioButtonFemale.isChecked()){
            radioButtonMale.setError("Please select e gender");
            radioButtonFemale.setError("Please select e gender");
            return false;
        }
        return true;
    }

    public boolean isCgpaValid(){
        String cgpa = editTextCgpa.getText().toString();
        if(cgpa.isEmpty()){
            editTextCgpa.setError("Please insert cgpa");
            return false;
        } else {
            double doubleCgpa = Double.parseDouble(cgpa);
            if(doubleCgpa < 0 || doubleCgpa > 4.0){
                editTextCgpa.setError("Please insert valid cgpa");
                return false;
            }
        }
        return true;
    }
}
