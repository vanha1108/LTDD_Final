package com.laptrinhdidong.electroniccomunications.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.laptrinhdidong.electroniccomunications.R;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdminFragment extends Fragment {

    private Dialog dialog;
    private FloatingActionButton btnShowDialog;
    private Button btnClose, btnSave;
    private EditText edtName, edtCredit;

    private Spinner spinnerFalcutyDialog, spinnerFal;
    private List<String> falcutyNames = new ArrayList<>();
    private ArrayAdapter<String> adapterFalcuty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject_admin, container, false);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_subject);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        btnShowDialog = view.findViewById(R.id.btnShowDialogAddSubject);
        btnSave = dialog.findViewById(R.id.btnSaveSubject);
        btnClose = dialog.findViewById(R.id.btnCloseDialogSubject);
        edtName = dialog.findViewById(R.id.edtSubjectNameDialog);
        edtCredit = dialog.findViewById(R.id.edtCreditDialog);
        spinnerFalcutyDialog = dialog.findViewById(R.id.spinnerFalDialogAddSubject);
        spinnerFal = view.findViewById(R.id.spinnerFalSubjectAdmin);

        // Load spinnerFalcuty
        falcutyNames.add("Công nghệ thông tin");
        falcutyNames.add("Kinh tế");
        falcutyNames.add("Ô tô");
        falcutyNames.add("Cơ khí");
        adapterFalcuty = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, falcutyNames);
        adapterFalcuty.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerFalcutyDialog.setAdapter(adapterFalcuty);

        // Load spinnerFal
        spinnerFal.setAdapter(adapterFalcuty);

        btnShowDialog.setOnClickListener(v -> {
            dialog.show();
        });

        btnClose.setOnClickListener(v -> {
            dialog.dismiss();
        });

        return view;
    }
}