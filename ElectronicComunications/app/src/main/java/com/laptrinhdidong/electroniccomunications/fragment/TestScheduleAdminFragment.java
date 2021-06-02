package com.laptrinhdidong.electroniccomunications.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.laptrinhdidong.electroniccomunications.R;

public class TestScheduleAdminFragment extends Fragment {

    private Dialog dialog;
    private FloatingActionButton btnShowDialog;
    private TextView txtDate;
    private Spinner spinnerFalcuty, spinnerClass, spinnerTeacher, spinnerSubject, spinnerStart, spinnerNumber;
    private Button btnSave, btnClose;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_schedule, container, false);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_testschedule);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        btnShowDialog = view.findViewById(R.id.btnShowDialogAddTestSchdule);
        btnSave = dialog.findViewById(R.id.btnSaveTestschedule);
        btnClose = dialog.findViewById(R.id.btnCloseDialogTestSchedule);
        txtDate = dialog.findViewById(R.id.txtDateDialogTestSchedule);
        spinnerFalcuty = dialog.findViewById(R.id.spinnerFacultyDialogTestSchedule);
        spinnerClass = dialog.findViewById(R.id.spinnerClassDialogTestSchedule);
        spinnerTeacher = dialog.findViewById(R.id.spinnerTeacherDialogTestSchedule);
        spinnerSubject = dialog.findViewById(R.id.spinnerSubjectNameDialogTestSchedule);
        spinnerStart = dialog.findViewById(R.id.spinnerStartTestSchedule);
        spinnerNumber = dialog.findViewById(R.id.spinnerNumberOfLessonTestSchedule);

        btnShowDialog.setOnClickListener(v-> {
            dialog.show();
        });

        btnClose.setOnClickListener(v-> {
            dialog.dismiss();
        });



        return view;
    }
}