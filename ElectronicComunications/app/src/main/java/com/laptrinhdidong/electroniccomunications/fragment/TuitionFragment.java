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

import com.laptrinhdidong.electroniccomunications.adapter.TuitionAdapter;

import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.model.Classes;
import com.laptrinhdidong.electroniccomunications.model.Tuition;



import java.util.ArrayList;

public class TuitionFragment extends Fragment {

    private Spinner spinnerFacultyTuitionAdmin, spinnerClassesTuitionAdmin, spinnerStudentTuitionAdmin;
    private RecyclerView recyclerView;
    String s1[], s2[], s3[];

    DatabaseReference mData;
    DatabaseReference mData2;
    ArrayList<Tuition> tuitions;
    TuitionAdapter myAdapter;



    public TuitionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tuition_admin, container, false);
        spinnerFacultyTuitionAdmin = (Spinner)view.findViewById(R.id.spinnerTuitionFaculty);
        spinnerClassesTuitionAdmin = (Spinner)view.findViewById(R.id.spinnerTuitionClasses);
        spinnerStudentTuitionAdmin = (Spinner)view.findViewById(R.id.spinnerTuitionStudent);

        // spinner
        ArrayList<String> arrayClass2 = new ArrayList<String>();

        ArrayList<String> arrayFaculty = new ArrayList<String>();
        ArrayList<String> arrayClass = new ArrayList<String>();
        ArrayList<String> arrayStudent = new ArrayList<String>();

        arrayFaculty.add("Information Technology");
        arrayFaculty.add("Computer Engineering");

        arrayClass.add("17110CLS2");
        arrayClass.add("17110CLS3");

        arrayStudent.add("17110125");
        arrayStudent.add("17110126");

        mData2 = FirebaseDatabase.getInstance().getReference();
        mData2.child("class").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Classes newClass = dataSnapshot.getValue(Classes.class);

                    arrayClass2.add(newClass.getClassName());
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayFaculty);
        spinnerFacultyTuitionAdmin.setAdapter(arrayAdapter);

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayClass2);
        spinnerClassesTuitionAdmin.setAdapter(arrayAdapter2);

        ArrayAdapter arrayAdapter3 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayStudent);
        spinnerStudentTuitionAdmin.setAdapter(arrayAdapter3);


        mData= FirebaseDatabase.getInstance().getReference();

        // recyclerview
        recyclerView = view.findViewById(R.id.fragmentTuitionRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tuitions = new ArrayList<>();


        myAdapter = new TuitionAdapter(getContext(), tuitions);
        recyclerView.setAdapter(myAdapter);
        mData.child("tuition").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Tuition newTuition = dataSnapshot.getValue(Tuition.class);
                    tuitions.add(newTuition);
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