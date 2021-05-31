package com.laptrinhdidong.electroniccomunications.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
    private ArrayAdapter<String> adapterFalcuty;

    private List<SubjectEntity> subjects = new ArrayList<>();
    private SubjectAdapter subjectAdapter;
    private RecyclerView recyclerSubject;

    private String falName;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseRoot = firebaseDatabase.getReference();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData(databaseRoot);
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

        subjectAdapter = new SubjectAdapter(new SubjectAdapter.OnLongClick() {
            @Override
            public void showDialog(SubjectEntity subjectEntity) {
                showDialog(subjectEntity);
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
                loadData(databaseRoot);
                subjectAdapter.notifyDataSetChanged();
                edtName.setText("");
                edtCredit.setText("");
                edtMoney.setText("");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
        });

        spinnerFal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = facultyNames.get(position);
                reload(name, databaseRoot);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    public void loadData(DatabaseReference databaseRoot) {

        databaseRoot.child("Faculty").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                facultyNames.clear();
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
                if (facultyNames != null && facultyNames.size() > 0) {
                    falName = spinnerFal.getSelectedItem().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseRoot.child("subject").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                subjects.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String faculty = dataSnapshot.child("facultyName").getValue(String.class);
                    String subjectName = dataSnapshot.child("name").getValue(String.class);
                    String sCredit = dataSnapshot.child("credit").getValue(String.class);
                    String sMoney = dataSnapshot.child("moneyPerCredit").getValue(String.class);
                    try {
                        if (subjectName != "" && sCredit != "" && sMoney != "" && falName.equals(faculty)) {
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
    }

    public void reload(String facultyName, DatabaseReference databaseRoot) {
        databaseRoot.child("subject").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                subjects.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String faculty = dataSnapshot.child("facultyName").getValue(String.class);
                    String subjectName = dataSnapshot.child("name").getValue(String.class);
                    String sCredit = dataSnapshot.child("credit").getValue(String.class);
                    String sMoney = dataSnapshot.child("moneyPerCredit").getValue(String.class);
                    try {
                        if (subjectName != "" && sCredit != "" && sMoney != "" && facultyName.equals(faculty)) {
                            SubjectEntity subjectEntity = new SubjectEntity(subjectName, sCredit, sMoney);
                            subjects.add(subjectEntity);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                subjectAdapter.setData(subjects);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void showDialog(SubjectEntity subjectEntity) {
        Dialog dialog = new Dialog(getContext());
        dialog.setTitle("");
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_opitons);
        dialog.show();
        TextView txtUpdate = dialog.findViewById(R.id.txtUpdateOption);
        TextView txtDelete = dialog.findViewById(R.id.txtDeleteOption);

        txtUpdate.setOnClickListener(v->{

        });

        txtDelete.setOnClickListener(v->{

        });
    }

}