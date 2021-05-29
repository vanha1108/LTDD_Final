package com.laptrinhdidong.electroniccomunications.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.laptrinhdidong.electroniccomunications.R;

public class TimeTableAdminFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_time_table_admin, container, false);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_time_table);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        btnShowDialog = view.findViewById(R.id.btnShowDialogAddTimeTable);
        btnSave = dialog.findViewById(R.id.btnSaveTimeTable);
        btnClose = dialog.findViewById(R.id.btnCloseDialogTimeTable);
        txtDate = dialog.findViewById(R.id.txtDateDialogTimeTable);
        spinnerFalcuty = dialog.findViewById(R.id.spinnerFacultyDialogTimeTable);
        spinnerClass = dialog.findViewById(R.id.spinnerClassDialogTimeTable);
        spinnerTeacher = dialog.findViewById(R.id.spinnerTeacherDialogTimeTable);
        spinnerSubject = dialog.findViewById(R.id.spinnerSubjectNameDialogTimeTable);
        spinnerStart = dialog.findViewById(R.id.spinnerStartDialogTimeTable);
        spinnerNumber = dialog.findViewById(R.id.spinnerNumberOfLesson);

        btnShowDialog.setOnClickListener(v-> {
            dialog.show();
        });

        btnClose.setOnClickListener(v-> {
            dialog.dismiss();
        });



        return view;
    }
}