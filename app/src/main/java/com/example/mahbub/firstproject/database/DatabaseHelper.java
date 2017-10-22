package com.example.mahbub.firstproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mahbub.firstproject.Model.Student;

import java.util.ArrayList;

/**
 * Created by MAHBUB on 13-Oct-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "srmproject.db";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        TableAttributes tableAttributes = new TableAttributes();
        String query = tableAttributes.tableCreation();
        try {
            sqLiteDatabase.execSQL(query);
            Log.i("Table Create", "Done");
        } catch (SQLException e){
            Log.e("SQL Error", e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void registerNewStudent(Student student){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableAttributes.REGISTRATION_NAME, student.getName());
        contentValues.put(TableAttributes.REGISTRATION_PASSWORD, student.getPassword());
        contentValues.put(TableAttributes.REGISTRATION_ID, student.getId());
        contentValues.put(TableAttributes.REGISTRATION_EMAIL, student.getEmail());
        contentValues.put(TableAttributes.REGISTRATION_PHONE, student.getPhone());
        contentValues.put(TableAttributes.REGISTRATION_GENDER, student.getGender());
        contentValues.put(TableAttributes.REGISTRATION_CGPA, student.getCgpa());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            sqLiteDatabase.insert(TableAttributes.TABLE_NAME, null, contentValues);
            Log.i("Insert", "Insert Done");
        } catch (SQLException e){
            Log.e("SQL Error", e.toString());
        }
    }

    public String getPassByMail(String email)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT password From " + TableAttributes.TABLE_NAME + " Where " + TableAttributes.REGISTRATION_EMAIL + " = '" + email + "'";
        Cursor cursor=sqLiteDatabase.rawQuery(query, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return null;
        } else {
            cursor.moveToFirst();
            String pass = cursor.getString(cursor.getColumnIndex(TableAttributes.REGISTRATION_PASSWORD));
            cursor.close();
            return pass;
        }
    }

    public Student getSingleStudentByEmail(String email){
        Student student = new Student();
        String query = "SELECT * From " + TableAttributes.TABLE_NAME + " Where " + TableAttributes.REGISTRATION_EMAIL + " = '" + email + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        student.setName(cursor.getString(cursor.getColumnIndex(TableAttributes.REGISTRATION_NAME)));
        student.setPassword(cursor.getString(cursor.getColumnIndex(TableAttributes.REGISTRATION_PASSWORD)));
        student.setId(cursor.getString(cursor.getColumnIndex(TableAttributes.REGISTRATION_ID)));
        student.setEmail(cursor.getString(cursor.getColumnIndex(TableAttributes.REGISTRATION_EMAIL)));
        student.setPhone(cursor.getString(cursor.getColumnIndex(TableAttributes.REGISTRATION_PHONE)));
        student.setGender(cursor.getString(cursor.getColumnIndex(TableAttributes.REGISTRATION_GENDER)));
        student.setCgpa(Double.parseDouble(cursor.getString(cursor.getColumnIndex(TableAttributes.REGISTRATION_CGPA))));

        cursor.close();
        return student;
    }

    public ArrayList<Student> getAllStudent(){

        ArrayList<Student> students = new ArrayList<Student>();

        String query = "SELECT * From " + TableAttributes.TABLE_NAME + "";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                Student student = new Student();
                student.setName(cursor.getString(cursor.getColumnIndex(TableAttributes.REGISTRATION_NAME)));
                student.setPassword(cursor.getString(cursor.getColumnIndex(TableAttributes.REGISTRATION_PASSWORD)));
                student.setId(cursor.getString(cursor.getColumnIndex(TableAttributes.REGISTRATION_ID)));
                student.setEmail(cursor.getString(cursor.getColumnIndex(TableAttributes.REGISTRATION_EMAIL)));
                student.setPhone(cursor.getString(cursor.getColumnIndex(TableAttributes.REGISTRATION_PHONE)));
                student.setGender(cursor.getString(cursor.getColumnIndex(TableAttributes.REGISTRATION_GENDER)));
                student.setCgpa(Double.parseDouble(cursor.getString(cursor.getColumnIndex(TableAttributes.REGISTRATION_CGPA))));
                students.add(student);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return students;
    }
}
