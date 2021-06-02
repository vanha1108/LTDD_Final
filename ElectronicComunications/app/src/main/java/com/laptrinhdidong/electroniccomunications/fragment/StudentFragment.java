package com.laptrinhdidong.electroniccomunications.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import com.laptrinhdidong.electroniccomunications.adapter.StudentAdapter;

import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.model.Student;



import java.util.ArrayList;

public class StudentFragment extends Fragment {

    private Spinner spinnerFacultyStudentAdmin;
    private RecyclerView recyclerView;
    private Dialog dialog;
    private StudentAdapter myAdapter;

    ArrayList<Student> newStudents;
    DatabaseReference mData;


    public StudentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_admin, container, false);
        // Inflate the layout for this fragment
        mData= FirebaseDatabase.getInstance().getReference();
        // spinner
        spinnerFacultyStudentAdmin = (Spinner)view.findViewById(R.id.spinnerClass);
        ArrayList<String> arrayClassFaculty = new ArrayList<String>();
        arrayClassFaculty.add("Information Technology");
        arrayClassFaculty.add("Computer Engineering");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayClassFaculty);
        spinnerFacultyStudentAdmin.setAdapter(arrayAdapter);


        // recyclerview
        recyclerView = view.findViewById(R.id.studentAdminRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newStudents = new ArrayList<Student>();
        myAdapter = new StudentAdapter(getContext(), newStudents);
        recyclerView.setAdapter(myAdapter);
        mData.child("student").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Student newStudent = dataSnapshot.getValue(Student.class);
                    newStudents.add(newStudent);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

}