package com.laptrinhdidong.electroniccomunications.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.laptrinhdidong.electroniccomunications.activity.StudentUpdateAdmin;
import com.laptrinhdidong.electroniccomunications.adapter.StudentAdminAdapter;
import com.laptrinhdidong.electroniccomunications.model.Student;

import java.util.ArrayList;

public class StudentFragmentAdmin extends Fragment {

    private Spinner spinnerFacultyStudentAdmin;
    private RecyclerView recyclerView;
    private Dialog dialog;
    private StudentAdminAdapter myAdapter;
    private static final int MY_REQUEST_CODE = 10;
    ArrayList<Student> newStudents;
    DatabaseReference mData;
    FloatingActionButton fltButton;
    Button Add, Close;
    EditText edtStudentCode, edtFirstName, edtLastName, edtBoD, edtSex, edtAge, edtPhone, edtMail, edtClassID, edtFaculty;

    public StudentFragmentAdmin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_admin, container, false);
        // Inflate the layout for this fragment
        mData= FirebaseDatabase.getInstance().getReference();
        // spinner
        spinnerFacultyStudentAdmin = (Spinner)view.findViewById(R.id.spinnerClass);
        ArrayList<String> arrayClassFaculty = new ArrayList<String>();
        arrayClassFaculty.add("Information Technology");
        arrayClassFaculty.add("Computer Engineering");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayClassFaculty);
        spinnerFacultyStudentAdmin.setAdapter(arrayAdapter);

        // dialog add
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_student);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        // anh xa
        fltButton = view.findViewById(R.id.btnShowDialogAddStudent);
        Add = dialog.findViewById(R.id.btnAddStudent);
        Close = dialog.findViewById(R.id.btnCloseStudent);
        edtStudentCode = dialog.findViewById(R.id.edtAddStudentCode);
        edtFirstName = dialog.findViewById(R.id.edtAddFirstNameStudent);
        edtLastName = dialog.findViewById(R.id.edtAddStudentLastName);
        edtBoD = dialog.findViewById(R.id.edtAddDoBStudent);
        edtSex = dialog.findViewById(R.id.edtAddSexStudent);
        edtAge = dialog.findViewById(R.id.edtAddAgeStudent);
        edtPhone = dialog.findViewById(R.id.edtAddPhoneStudent);
        edtMail = dialog.findViewById(R.id.edtAddMailStudent);
        edtClassID = dialog.findViewById(R.id.edtAddClassIDStudent);
        edtFaculty = dialog.findViewById(R.id.edtAddFacultyStudent);

        // xu ly button
        fltButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtStudentCode.getText().toString().equals("") || edtFirstName.getText().toString().equals("") ||
                        edtLastName.getText().toString().equals("") || edtBoD.getText().toString().equals("") ||
                        edtSex.getText().toString().equals("") || edtAge.getText().toString().equals("") ||
                        edtPhone.getText().toString().equals("") || edtMail.getText().toString().equals("") ||
                        edtClassID.getText().toString().equals("") || edtFaculty.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Empty fields!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Student student = new Student(edtStudentCode.getText().toString().trim(), edtFirstName.getText().toString().trim(),
                        edtLastName.getText().toString().trim(), edtBoD.getText().toString().trim(), edtSex.getText().toString().trim(),
                        edtAge.getText().toString().trim(), edtPhone.getText().toString().trim(), edtMail.getText().toString().trim(),
                        edtClassID.getText().toString().trim(), edtFaculty.getText().toString().trim());
                mData.child("student").child(edtFirstName.getText().toString()).setValue(student);
                Toast.makeText(getContext(), "Add student successfully!", Toast.LENGTH_SHORT).show();

                loadData();
            }
        });

        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // recyclerview
        recyclerView = view.findViewById(R.id.studentAdminRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newStudents = new ArrayList<>();
        myAdapter = new StudentAdminAdapter(getContext(), newStudents, new StudentAdminAdapter.OnLongClick() {
            @Override
            public void dialogUpdateDeleteStudent(Student student) {
                dialogUpdateDelete(student);
            }
        });
        recyclerView.setAdapter(myAdapter);
        mData.child("student").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Student newStudent = dataSnapshot.getValue(Student.class);
                    newStudents.add(newStudent);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }

    private void dialogUpdateDelete(Student student) {
        Dialog dialog = new Dialog(getContext());
        dialog.setTitle("");
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_update_delete_student);
        dialog.show();

        TextView dialogUpdate = (TextView)dialog.findViewById(R.id.txtUpdateDialogStudent);
        TextView dialogDelete = (TextView)dialog.findViewById(R.id.txtDeleteDialogStudent);

        dialogUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StudentUpdateAdmin.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_student", student);
                intent.putExtras(bundle);
                startActivityForResult(intent, MY_REQUEST_CODE);
            }
        });

        dialogDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.child("student").child(student.getFirstName().toString()).setValue(null);
                loadData();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            loadData();
        }
    }

    private void loadData() {
        ArrayList<Student> student2 = new ArrayList<>();
        mData.child("student").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Student newStudent = dataSnapshot.getValue(Student.class);
                    student2.add(newStudent);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myAdapter.setData(student2);
    }
}