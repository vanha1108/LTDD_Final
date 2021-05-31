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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.adapter.SubjectAdapter;
import com.laptrinhdidong.electroniccomunications.model.FacultyEntity;
import com.laptrinhdidong.electroniccomunications.model.SubjectEntity;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdminFragment extends Fragment {

    private Dialog dialog;
    private FloatingActionButton btnShowDialog;
    private Button btnClose, btnSave;
    private EditText edtName, edtCredit, edtMoney;

    private Spinner spinnerFalcutyDialog, spinnerFal;
    private List<String> facultyNames = new ArrayList<>();
    private List<FacultyEntity> facultyEntities = new ArrayList<>();
    private ArrayAdapter<String> adapterFalcuty;

    private List<SubjectEntity> subjects = new ArrayList<>();
    private SubjectAdapter subjectAdapter;
    private RecyclerView recyclerSubject;

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
        edtMoney = dialog.findViewById(R.id.edtMoneyCreditDialog);
        spinnerFalcutyDialog = dialog.findViewById(R.id.spinnerFalDialogAddSubject);
        spinnerFal = view.findViewById(R.id.spinnerFalSubjectAdmin);
        recyclerSubject = view.findViewById(R.id.recyclerSubjectAdmin);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseRoot = firebaseDatabase.getReference();

        databaseRoot.child("Faculty").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String facultyName = dataSnapshot.child("facultyName").getValue(String.class);
                    if (facultyName != "") {
                        facultyNames.add(facultyName);
                    }
                }
                adapterFalcuty = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, facultyNames);
                adapterFalcuty.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerFalcutyDialog.setAdapter(adapterFalcuty);
                spinnerFal.setAdapter(adapterFalcuty);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseRoot.child("subject").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String subjectName = dataSnapshot.child("name").getValue(String.class);
                    String sCredit = dataSnapshot.child("credit").getValue(String.class);
                    String sMoney = dataSnapshot.child("moneyPerCredit").getValue(String.class);
                    try {
                        if (subjectName != "") {
                            SubjectEntity subjectEntity = new SubjectEntity(subjectName, sCredit, sMoney);
                            subjects.add(subjectEntity);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                adapterFalcuty = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, facultyNames);
                adapterFalcuty.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerFalcutyDialog.setAdapter(adapterFalcuty);
                spinnerFal.setAdapter(adapterFalcuty);

                // Load recyclerView
                subjectAdapter = new SubjectAdapter(subjects, getContext());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                recyclerSubject.setLayoutManager(linearLayoutManager);
                recyclerSubject.setAdapter(subjectAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnShowDialog.setOnClickListener(v -> {
            dialog.show();
        });

        btnClose.setOnClickListener(v -> {
            dialog.dismiss();
        });

        btnSave.setOnClickListener(v -> {
            String facultyName = spinnerFalcutyDialog.getSelectedItem().toString();
            String nameSubject = edtName.getText().toString().trim();
            String sCredit = edtCredit.getText().toString();
            String sMoney = edtMoney.getText().toString();
            if (facultyName.equals("") || nameSubject.equals("") || sCredit.equals("") || sMoney.equals("")) {
                Toast.makeText(getContext(), "Please enter the full information", Toast.LENGTH_LONG).show();
                return;
            }
            try {
                SubjectEntity subject = new SubjectEntity(facultyName, nameSubject, sCredit, sMoney);
                databaseRoot.child("subject").push().setValue(subject);
                Toast.makeText(getContext(), "Add subject successfull", Toast.LENGTH_LONG).show();
                edtName.setText("");
                edtCredit.setText("");
                edtMoney.setText("");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
        });
        return view;
    }
}