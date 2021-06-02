package com.laptrinhdidong.electroniccomunications.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.Spinner;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.laptrinhdidong.electroniccomunications.R;

import com.laptrinhdidong.electroniccomunications.adapter.ClassAdapter;

import com.laptrinhdidong.electroniccomunications.model.Classes;


import java.util.ArrayList;

public class ClassFragment extends Fragment {
    private Spinner spinnerClass;
    private RecyclerView recyclerView;
    private ClassAdapter myAdapter;

    DatabaseReference mData;
    ArrayList<Classes> newClasses;


    public ClassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);




        // recyclerview
        mData= FirebaseDatabase.getInstance().getReference();
        recyclerView = view.findViewById(R.id.classRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newClasses = new  ArrayList<>();
        myAdapter = new ClassAdapter(getContext(), newClasses);
        recyclerView.setAdapter(myAdapter);

        mData.child("class").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Classes newClass2 = dataSnapshot.getValue(Classes.class);
                    newClasses.add(newClass2);
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