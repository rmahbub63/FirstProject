package com.example.mahbub.firstproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mahbub.firstproject.Model.Student;
import com.example.mahbub.firstproject.R;

import java.util.ArrayList;

/**
 * Created by MAHBUB on 20-Oct-17.
 */

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Student> students;

    public CustomAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View custom_view = layoutInflater.inflate(R.layout.custom_adapter_layout, null);

        TextView textViewName = (TextView) custom_view.findViewById(R.id.textViewName);
        TextView textViewEmail = (TextView) custom_view.findViewById(R.id.textViewEmail);
        TextView textViewCgpa = (TextView) custom_view.findViewById(R.id.textViewCgpa);

        textViewName.setText(students.get(i).getName());
        textViewEmail.setText(students.get(i).getEmail());
        textViewCgpa.setText(String.valueOf(students.get(i).getCgpa()));

        return custom_view;
    }
}
