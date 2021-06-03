package com.laptrinhdidong.electroniccomunications.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.adapter.TimeTableAdapter;
import com.laptrinhdidong.electroniccomunications.model.TimeTableEntity;

import java.util.ArrayList;
import java.util.List;

public class TimeTableViewFragment extends Fragment {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseRoot = firebaseDatabase.getReference();

    private static final String SESSION = "SESSION";

    private RecyclerView recyclerTimeTable;
    private TimeTableAdapter timeTableAdapter;
    private List<TimeTableEntity> timeTables = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_table_view, container, false);
        recyclerTimeTable = view.findViewById(R.id.recyclerTimeTableView);
        timeTableAdapter = new TimeTableAdapter(timeTables, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerTimeTable.setLayoutManager(linearLayoutManager);
        recyclerTimeTable.setAdapter(timeTableAdapter);
        return view;
    }

    public void loadData() {
        SharedPreferences preferences = getContext().getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        String clName = preferences.getString("ClassName", "");
        databaseRoot.child("TimeTable").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                timeTables.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String key = dataSnapshot.getKey();
                    String className = dataSnapshot.child("className").getValue(String.class);
                    String teacherName = dataSnapshot.child("teacherName").getValue(String.class);
                    String subjectName = dataSnapshot.child("subjectName").getValue(String.class);
                    String roomName = dataSnapshot.child("roomName").getValue(String.class);
                    String sStartLesson = dataSnapshot.child("startLesson").getValue(String.class);
                    String sNumberLesson = dataSnapshot.child("numberLesson").getValue(String.class);
                    try {
                        if (key != "" && !className.equals("") && !teacherName.equals("") && !subjectName.equals("") && !roomName.equals("")
                                && !sStartLesson.equals("") && !sNumberLesson.equals("") && className.equals(clName)) {
                            TimeTableEntity timeTableEntity = new TimeTableEntity(key, className, teacherName, subjectName, roomName, sStartLesson, sNumberLesson);
                            timeTables.add(timeTableEntity);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}