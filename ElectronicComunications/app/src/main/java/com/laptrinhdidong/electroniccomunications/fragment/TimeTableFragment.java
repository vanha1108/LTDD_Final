package com.laptrinhdidong.electroniccomunications.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.laptrinhdidong.electroniccomunications.R;

import java.util.ArrayList;
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

    private List<String> falcutyNames = new ArrayList<>();
    private List<String> classNames = new ArrayList<>();
    private List<String> teacherNames = new ArrayList<>();
    private List<String> subjectNames = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        // Load spinnerFalcuty
        falcutyNames.add("Công nghệ thông tin");
        falcutyNames.add("Kinh tế");
        falcutyNames.add("Ô tô");
        falcutyNames.add("Cơ khí");
        adapterFalcuty = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, falcutyNames);
        adapterFalcuty.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerFacultyDialog.setAdapter(adapterFalcuty);

        // Load spinnerClass
        classNames.add("171101CL1A");
        classNames.add("171101CL1B");
        classNames.add("171101CL2A");
        classNames.add("171101CL2B");
        adapterClass = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, classNames);
        adapterClass.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerClassDialog.setAdapter(adapterClass);

        // Load spinnerTeacher
        teacherNames.add("Nguyen Dang Quang");
        teacherNames.add("Nguyen Tran Thi Van");
        teacherNames.add("Tran Cong Tu");
        teacherNames.add("Nguyen Thanh Phuoc");
        adapterTeacher = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, teacherNames);
        adapterTeacher.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerTeacherDialog.setAdapter(adapterTeacher);

        // Load spinnerSubject
        subjectNames.add("Lap Trinh Di Dong");
        subjectNames.add("Anh van 4");
        subjectNames.add("Toan 1");
        subjectNames.add("Toan 2");
        adapterSubject = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, subjectNames);
        adapterSubject.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSubjectDialog.setAdapter(adapterSubject);

        // Load spinnerFaculty
        spinnerFaculty.setAdapter(adapterFalcuty);

        // Load spinnerClass
        spinnerClass.setAdapter(adapterClass);


        btnShowDialog.setOnClickListener(v -> {
            dialog.show();
        });

        btnClose.setOnClickListener(v -> {
            dialog.dismiss();
        });

        return view;
    }
}