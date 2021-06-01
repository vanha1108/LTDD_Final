package com.laptrinhdidong.electroniccomunications.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.laptrinhdidong.electroniccomunications.activity.TeacherUpdateAdmin;
import com.laptrinhdidong.electroniccomunications.adapter.TeacherAdminAdapter;
import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.model.Teacher;


import java.util.ArrayList;

public class TeacherFragmentAdmin extends Fragment {

    private Spinner spinnerFacultyTeacherAdmin;
    private RecyclerView recyclerView;
    private Dialog dialog;
    private TeacherAdminAdapter myAdapter;
    private static final int MY_REQUEST_CODE = 10;
    ArrayList<Teacher> newTeachers;
    DatabaseReference mData;
    FloatingActionButton fltButton;
    Button Add, Close;
    EditText edtFirstName, edtLastName, edtBoD, edtSex, edtAge, edtPhone, edtMail, edtClassID, edtFaculty;


    public TeacherFragmentAdmin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_admin, container, false);
        // Inflate the layout for this fragment
        mData= FirebaseDatabase.getInstance().getReference();
        // spinner
        spinnerFacultyTeacherAdmin = (Spinner)view.findViewById(R.id.spinnerClass);
        ArrayList<String> arrayClassFaculty = new ArrayList<String>();
        arrayClassFaculty.add("Information Technology");
        arrayClassFaculty.add("Computer Engineering");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayClassFaculty);
        spinnerFacultyTeacherAdmin.setAdapter(arrayAdapter);

        // dialog add
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_teacher);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        // anh xa
        fltButton = view.findViewById(R.id.btnShowDialogAddTeacher);
        Add = dialog.findViewById(R.id.btnAddTeacher);
        Close = dialog.findViewById(R.id.btnCloseTeacher);
        edtFirstName = dialog.findViewById(R.id.edtAddFirstNameTeacher);
        edtLastName = dialog.findViewById(R.id.edtAddTeacherLastName);
        edtBoD = dialog.findViewById(R.id.edtAddDoB);
        edtSex = dialog.findViewById(R.id.edtAddSexTeacher);
        edtAge = dialog.findViewById(R.id.edtAddAgeTeacher);
        edtPhone = dialog.findViewById(R.id.edtAddPhoneTeacher);
        edtMail = dialog.findViewById(R.id.edtAddMailTeacher);
        edtClassID = dialog.findViewById(R.id.edtAddClassIDTeacher);
        edtFaculty = dialog.findViewById(R.id.edtAddFacultyTeacher);

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
                if (edtFirstName.getText().toString().equals("") || edtLastName.getText().toString().equals("") ||
                        edtBoD.getText().toString().equals("") || edtSex.getText().toString().equals("") || edtAge.getText().toString().equals("") ||
                        edtPhone.getText().toString().equals("") || edtMail.getText().toString().equals("") || edtClassID.getText().toString()
                        .equals("") || edtFaculty.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Empty fields!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Teacher teacher = new Teacher(edtFirstName.getText().toString().trim(), edtLastName.getText().toString().trim(), edtBoD.getText()
                        .toString().trim(), edtSex.getText().toString().trim(), edtAge.getText().toString().trim(), edtPhone.getText().toString().trim(),
                        edtMail.getText().toString().trim(), edtClassID.getText().toString().trim(), edtFaculty.getText().toString().trim());
                mData.child("teacher").child(edtFirstName.getText().toString()).setValue(teacher);
                Toast.makeText(getContext(), "Add teacher successfully!", Toast.LENGTH_SHORT).show();

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
        recyclerView = view.findViewById(R.id.teacherAdminRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newTeachers = new ArrayList<>();
        myAdapter = new TeacherAdminAdapter(getContext(), newTeachers, new TeacherAdminAdapter.OnLongClick() {
            @Override
            public void dialogUpdateDeleteTeacher(Teacher teacher) {
                dialogUpdateDelete(teacher);
            }
        });
        recyclerView.setAdapter(myAdapter);
        mData.child("teacher").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Teacher newTeacher = dataSnapshot.getValue(Teacher.class);
                    newTeachers.add(newTeacher);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }

    private void dialogUpdateDelete(Teacher teacher) {
        Dialog dialog = new Dialog(getContext());
        dialog.setTitle("");
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_update_delete_teacher);
        dialog.show();

        TextView dialogUpdate = (TextView)dialog.findViewById(R.id.txtUpdateDialogTeacher);
        TextView dialogDelete = (TextView)dialog.findViewById(R.id.txtDeleteDialogTeacher);

        dialogUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TeacherUpdateAdmin.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_teacher", teacher);
                intent.putExtras(bundle);
                startActivityForResult(intent, MY_REQUEST_CODE);
            }
        });

        dialogDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.child("teacher").child(teacher.getFirstName().toString()).setValue(null);
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
        ArrayList<Teacher> teacher2 = new ArrayList<>();
        mData.child("teacher").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Teacher newTeacher = dataSnapshot.getValue(Teacher.class);
                    teacher2.add(newTeacher);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myAdapter.setData(teacher2);
    }
}