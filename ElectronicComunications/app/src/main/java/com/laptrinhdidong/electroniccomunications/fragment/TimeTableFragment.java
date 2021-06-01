package com.laptrinhdidong.electroniccomunications.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.laptrinhdidong.electroniccomunications.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimeTableFragment extends Fragment {

    private Dialog dialog;
    private FloatingActionButton btnShowDialog;
    private TextView txtDate;
    private Spinner spinnerFacultyDialog, spinnerClassDialog, spinnerTeacherDialog, spinnerSubjectDialog,
            spinnerStartDialog, spinnerNumberDialog, spinnerFaculty, spinnerClass;
    private Button btnSave, btnClose;
    private String[] lessons, numberLessons;
    private ArrayAdapter<String> adapterLessons, adapterNumberLesson, adapterFalcuty, adapterClass, adapterTeacher, adapterSubject;

    private List<String> facultyNames = new ArrayList<>();
    private List<String> classNames = new ArrayList<>();
    private List<String> teacherNames = new ArrayList<>();
    private List<String> subjectNames = new ArrayList<>();

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseRoot = firebaseDatabase.getReference();

    private String facultyName = "";
    private String className = "";

    private Calendar calendar;
    private SimpleDateFormat spdf = new SimpleDateFormat("dd/MM/yyyy");


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData(databaseRoot);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_table, container, false);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_time_table);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        btnShowDialog = view.findViewById(R.id.btnShowDialogAddTimeTable);
        btnSave = dialog.findViewById(R.id.btnSaveTimeTable);
        btnClose = dialog.findViewById(R.id.btnCloseDialogTimeTable);
        txtDate = dialog.findViewById(R.id.txtDateDialogTimeTable);
        spinnerFacultyDialog = dialog.findViewById(R.id.spinnerFacultyDialogTimeTable);
        spinnerClassDialog = dialog.findViewById(R.id.spinnerClassDialogTimeTable);
        spinnerTeacherDialog = dialog.findViewById(R.id.spinnerTeacherDialogTimeTable);
        spinnerSubjectDialog = dialog.findViewById(R.id.spinnerSubjectNameDialogTimeTable);
        spinnerStartDialog = dialog.findViewById(R.id.spinnerStartDialogTimeTable);
        spinnerNumberDialog = dialog.findViewById(R.id.spinnerNumberOfLesson);

        spinnerFaculty = view.findViewById(R.id.spinner_faculty_add_time_table);
        spinnerClass = view.findViewById(R.id.spinner_class_add_time_table);

        calendar = Calendar.getInstance();
        txtDate.setText(spdf.format(calendar.getTime()));


        // Load spinnerStart
        lessons = getResources().getStringArray(R.array.lessons);
        adapterLessons = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, lessons);
        adapterLessons.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerStartDialog.setAdapter(adapterLessons);

        // Load spinnerNumber
        numberLessons = getResources().getStringArray(R.array.numberLesson);
        adapterNumberLesson = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, numberLessons);
        adapterNumberLesson.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerNumberDialog.setAdapter(adapterNumberLesson);

        btnShowDialog.setOnClickListener(v -> {
            dialog.show();
        });

        btnClose.setOnClickListener(v -> {
            dialog.dismiss();
        });

        return view;
    }

    public void loadData(DatabaseReference databaseRoot) {
        databaseRoot.child("Faculty").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                facultyNames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String falName = dataSnapshot.child("facultyName").getValue(String.class);
                    if (falName != "") {
                        facultyNames.add(falName);
                    }
                }
                adapterFalcuty = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, facultyNames);
                adapterFalcuty.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerFaculty.setAdapter(adapterFalcuty);
                spinnerFacultyDialog.setAdapter(adapterFalcuty);
                if (facultyNames != null && facultyNames.size() > 0) {
                    facultyName = spinnerFaculty.getSelectedItem().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseRoot.child("class").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                classNames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String clName = dataSnapshot.child("className").getValue(String.class);
                    if (clName != "") {
                        classNames.add(clName);
                    }
                }
                adapterClass = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, classNames);
                adapterClass.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerClass.setAdapter(adapterClass);
                spinnerClassDialog.setAdapter(adapterClass);
                if (classNames != null && classNames.size() > 0) {
                    className = spinnerClass.getSelectedItem().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseRoot.child("teacher").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                teacherNames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String firstName = dataSnapshot.child("firstName").getValue(String.class);
                    String lastName = dataSnapshot.child("lastName").getValue(String.class);
                    String techName = firstName + " " + lastName;
                    if (techName != "") {
                        teacherNames.add(techName);
                    }
                }
                adapterTeacher = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, teacherNames);
                adapterTeacher.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerTeacherDialog.setAdapter(adapterTeacher);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseRoot.child("subject").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                subjectNames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String subName = dataSnapshot.child("name").getValue(String.class);
                    if (subName != "") {
                        subjectNames.add(subName);
                    }
                }
                adapterSubject = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, subjectNames);
                adapterSubject.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerSubjectDialog.setAdapter(adapterSubject);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}