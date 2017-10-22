package com.example.mahbub.firstproject.database;

import android.util.Log;

/**
 * Created by MAHBUB on 13-Oct-17.
 */

public class TableAttributes {

    public static final String TABLE_NAME = "registration";
    public static final String REGISTRATION_NAME = "name";
    public static final String REGISTRATION_PASSWORD = "password";
    public static final String REGISTRATION_ID = "id";
    public static final String REGISTRATION_EMAIL = "email";
    public static final String REGISTRATION_PHONE = "phone";
    public static final String REGISTRATION_GENDER = "gender";
    public static final String REGISTRATION_CGPA = "cgpa";

    public String tableCreation(){
        String query = "CREATE TABLE " + TABLE_NAME + "(" + REGISTRATION_NAME + " TEXT, " + REGISTRATION_PASSWORD + " TEXT, " + REGISTRATION_ID + " TEXT, " +
                REGISTRATION_EMAIL + " TEXT, " + REGISTRATION_PHONE + " TEXT, " + REGISTRATION_GENDER + " TEXT, " + REGISTRATION_CGPA + " TEXT)";
        Log.d("Query", query);
        return query;
    }


}
