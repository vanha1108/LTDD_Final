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


import com.laptrinhdidong.electroniccomunications.adapter.TeacherAdapter;

import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.model.Teacher;



import java.util.ArrayList;

public class TeacherFragment extends Fragment {

    private Spinner spinnerFacultyTeacherAdmin;
    private RecyclerView recyclerView;
    private Dialog dialog;
    private TeacherAdapter myAdapter;

    ArrayList<Teacher> newTeachers;
    DatabaseReference mData;


    public TeacherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher, container, false);
        // Inflate the layout for this fragment
        mData= FirebaseDatabase.getInstance().getReference();



        // recyclerview
        recyclerView = view.findViewById(R.id.teacherRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newTeachers = new ArrayList<>();
        myAdapter = new TeacherAdapter(getContext(), newTeachers);
        recyclerView.setAdapter(myAdapter);
        mData.child("teacher").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Teacher newTeacher = dataSnapshot.getValue(Teacher.class);
                    newTeachers.add(newTeacher);
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