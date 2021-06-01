package com.laptrinhdidong.electroniccomunications.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.model.Teacher;

import java.util.HashMap;

public class TeacherUpdateAdmin extends AppCompatActivity {

    EditText edtEdtFirstNameTeacher, edtEdtTeacherLastName, edtEdtDoB, edtEdtSexTeacher, edtEdtAgeTeacher, edtEdtPhoneTeacher, edtEdtMailTeacher, edtEdtClassIDTeacher, edtEdtFacultyTeacher;
    Button btnEdtEdtTeacher, btnEdtCloseTeacher;
    Teacher newTeacher;
    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_update_admin);

        btnEdtCloseTeacher = (Button) findViewById(R.id.btEdtCloseTeacher);
        btnEdtEdtTeacher = (Button) findViewById(R.id.btnEdtEdtTeacher);
        edtEdtFirstNameTeacher = (EditText)findViewById(R.id.edtEdtFirstNameTeacher);
        edtEdtTeacherLastName = (EditText) findViewById(R.id.edtEdtTeacherLastName);
        edtEdtDoB = (EditText) findViewById(R.id.edtEdtDoB);
        edtEdtSexTeacher = (EditText) findViewById(R.id.edtEdtSexTeacher);
        edtEdtAgeTeacher = (EditText) findViewById(R.id.edtEdtAgeTeacher);
        edtEdtPhoneTeacher = (EditText) findViewById(R.id.edtEdtPhoneTeacher);
        edtEdtMailTeacher = (EditText) findViewById(R.id.edtEdtMailTeacher);
        edtEdtClassIDTeacher = (EditText) findViewById(R.id.edtEdtClassIDTeacher);
        edtEdtFacultyTeacher = (EditText) findViewById(R.id.edtEdtFacultyTeacher);

        newTeacher = (Teacher) getIntent().getExtras().get("object_teacher");

        if (newTeacher != null) {
            edtEdtFirstNameTeacher.setText(newTeacher.getFirstName());
            edtEdtTeacherLastName.setText(newTeacher.getLastName());
            edtEdtDoB.setText(newTeacher.getDoB());
            edtEdtSexTeacher.setText(newTeacher.getSex());
            edtEdtAgeTeacher.setText(newTeacher.getAge());
            edtEdtPhoneTeacher.setText(newTeacher.getPhone());
            edtEdtMailTeacher.setText(newTeacher.getGmail());
            edtEdtClassIDTeacher.setText(newTeacher.getClassID());
            edtEdtFacultyTeacher.setText(newTeacher.getFacultyID());

        }

        btnEdtEdtTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTeacher();
            }
        });

        btnEdtCloseTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void updateTeacher() {
        String edtFirstName = edtEdtFirstNameTeacher.getText().toString().trim();
        String edtLastName = edtEdtTeacherLastName.getText().toString().trim();
        String edtDoB = edtEdtDoB.getText().toString().trim();
        String edtSexTeacher = edtEdtSexTeacher.getText().toString().trim();
        String edtAgeTeacher = edtEdtAgeTeacher.getText().toString().trim();
        String edtPhoneTeacher = edtEdtPhoneTeacher.getText().toString().trim();
        String edtMailTeacher = edtEdtMailTeacher.getText().toString().trim();
        String edtClassIDTeacher = edtEdtClassIDTeacher.getText().toString().trim();
        String edtFacultyTeacher = edtEdtFacultyTeacher.getText().toString().trim();

        if(TextUtils.isEmpty(edtFirstName) || TextUtils.isEmpty(edtLastName) || TextUtils.isEmpty(edtDoB) || TextUtils.isEmpty(edtSexTeacher) ||
                TextUtils.isEmpty(edtAgeTeacher) || TextUtils.isEmpty(edtPhoneTeacher) || TextUtils.isEmpty(edtMailTeacher) ||
                TextUtils.isEmpty(edtClassIDTeacher) || TextUtils.isEmpty(edtFacultyTeacher)) {
            Toast.makeText(TeacherUpdateAdmin.this, "Empty fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        mData = FirebaseDatabase.getInstance().getReference();
        HashMap hashMap= new HashMap();
        hashMap.put("firstName", edtFirstName);
        hashMap.put("lastName", edtLastName);
        hashMap.put("doB", edtDoB);
        hashMap.put("sex", edtSexTeacher);
        hashMap.put("age", edtAgeTeacher);
        hashMap.put("phone", edtPhoneTeacher);
        hashMap.put("gmail", edtMailTeacher);
        hashMap.put("classID", edtClassIDTeacher);
        hashMap.put("facultyID", edtFacultyTeacher);

        mData.child("teacher").child(edtFirstName).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(TeacherUpdateAdmin.this, "Update successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(this, "Successfully Update!", Toast.LENGTH_SHORT).show();
        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }
}