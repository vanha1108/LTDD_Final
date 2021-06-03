package com.laptrinhdidong.electroniccomunications.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.model.Student;

import java.util.HashMap;

public class StudentUpdateAdmin extends AppCompatActivity {

    EditText edtEdtStudentCode, edtEdtFirstNameStudent, edtEdtStudentLastName, edtEdtDoBStudent, edtEdtSexStudent, edtEdtAgeStudent, edtEdtPhoneStudent,
            edtEdtMailStudent, edtEdtClassIDStudent, edtEdtFacultyStudent;
    Button btnEdtEdtStudent, btnEdtCloseStudent;
    Student newStudent;
    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_update_admin);

        btnEdtCloseStudent = (Button) findViewById(R.id.btEdtCloseStudent);
        btnEdtEdtStudent = (Button) findViewById(R.id.btnEdtEdtStudent);
        edtEdtStudentCode = (EditText)findViewById(R.id.edtStudentCode);
        edtEdtFirstNameStudent = (EditText)findViewById(R.id.edtEdtFirstNameStudent);
        edtEdtStudentLastName = (EditText) findViewById(R.id.edtEdtStudentLastName);
        edtEdtDoBStudent = (EditText) findViewById(R.id.edtEdtDoBStudent);
        edtEdtSexStudent = (EditText) findViewById(R.id.edtEdtSexStudent);
        edtEdtAgeStudent = (EditText) findViewById(R.id.edtEdtAgeStudent);
        edtEdtPhoneStudent = (EditText) findViewById(R.id.edtEdtPhoneStudent);
        edtEdtMailStudent = (EditText) findViewById(R.id.edtEdtMailStudent);
        edtEdtClassIDStudent = (EditText) findViewById(R.id.edtEdtClassIDStudent);
        edtEdtFacultyStudent = (EditText) findViewById(R.id.edtEdtFacultyStudent);

        newStudent = (Student) getIntent().getExtras().get("object_student");

        if (newStudent != null) {
            edtEdtStudentCode.setText(newStudent.getStudentCode());
            edtEdtFirstNameStudent.setText(newStudent.getFirstName());
            edtEdtStudentLastName.setText(newStudent.getLastName());
            edtEdtDoBStudent.setText(newStudent.getDoB());
            edtEdtSexStudent.setText(newStudent.getSex());
            edtEdtAgeStudent.setText(newStudent.getAge());
            edtEdtPhoneStudent.setText(newStudent.getPhone());
            edtEdtMailStudent.setText(newStudent.getGmail());
            edtEdtClassIDStudent.setText(newStudent.getClassID());
            edtEdtFacultyStudent.setText(newStudent.getFacultyID());

        }

        btnEdtEdtStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudent();
            }
        });

        btnEdtCloseStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void updateStudent() {
        String edtStudentCode = edtEdtStudentCode.getText().toString().trim();
        String edtFirstNameStudent = edtEdtFirstNameStudent.getText().toString().trim();
        String edtLastNameStudent = edtEdtStudentLastName.getText().toString().trim();
        String edtDoBStudent = edtEdtDoBStudent.getText().toString().trim();
        String edtSexStudent = edtEdtSexStudent.getText().toString().trim();
        String edtAgeStudent = edtEdtAgeStudent.getText().toString().trim();
        String edtPhoneStudent = edtEdtPhoneStudent.getText().toString().trim();
        String edtMailStudent = edtEdtMailStudent.getText().toString().trim();
        String edtClassIDStudent = edtEdtClassIDStudent.getText().toString().trim();
        String edtFacultyStudent = edtEdtFacultyStudent.getText().toString().trim();

        if(TextUtils.isEmpty(edtFirstNameStudent) || TextUtils.isEmpty(edtLastNameStudent) || TextUtils.isEmpty(edtDoBStudent) || TextUtils.isEmpty(edtSexStudent) ||
                TextUtils.isEmpty(edtAgeStudent) || TextUtils.isEmpty(edtPhoneStudent) || TextUtils.isEmpty(edtMailStudent) ||
                TextUtils.isEmpty(edtClassIDStudent) || TextUtils.isEmpty(edtFacultyStudent)) {
            Toast.makeText(StudentUpdateAdmin.this, "Empty fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        mData = FirebaseDatabase.getInstance().getReference();
        HashMap hashMap= new HashMap();
        hashMap.put("studentCode", edtStudentCode);
        hashMap.put("firstName", edtFirstNameStudent);
        hashMap.put("lastName", edtLastNameStudent);
        hashMap.put("doB", edtDoBStudent);
        hashMap.put("sex", edtSexStudent);
        hashMap.put("age", edtAgeStudent);
        hashMap.put("phone", edtPhoneStudent);
        hashMap.put("gmail", edtMailStudent);
        hashMap.put("classID", edtClassIDStudent);
        hashMap.put("facultyID", edtFacultyStudent);

        mData.child("student").child(edtFirstNameStudent).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(StudentUpdateAdmin.this, "Update successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(this, "Successfully Update!", Toast.LENGTH_SHORT).show();
        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }
}