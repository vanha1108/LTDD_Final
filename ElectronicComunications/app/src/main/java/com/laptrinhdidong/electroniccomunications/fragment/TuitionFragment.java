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
        View view = inflater.inflate(R.layout.fragment_tuition, container, false);



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