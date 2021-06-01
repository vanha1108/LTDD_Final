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
import com.laptrinhdidong.electroniccomunications.model.Classes;

import java.util.HashMap;

public class
ClassUpdateAdmin extends AppCompatActivity {

    EditText edtNo, edtClassName, edtNoS, edtFacultyName;
    Button btnUpdate, btnClose;
    Classes newClass;
    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_update_admin);

        edtNo = (EditText)findViewById(R.id.edtEditClassID);
        edtClassName = (EditText)findViewById(R.id.edtEditClassName);
        edtNoS = (EditText)findViewById(R.id.edtEditClassNoS);
        edtFacultyName = (EditText)findViewById(R.id.edtEditClassFacultyName);
        btnUpdate = (Button)findViewById(R.id.btnAddClass);
        btnClose = (Button)findViewById(R.id.btnCloseClass);

        newClass = (Classes) getIntent().getExtras().get("object_class");

        if (newClass != null) {
            edtNo.setText(Integer.toString(newClass.getClassID()));
            edtClassName.setText(newClass.getClassName());
            edtNoS.setText(Integer.toString(newClass.getNos()));
            edtFacultyName.setText(newClass.getFacultyName());
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateClass();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void updateClass() {
        String No = edtNo.getText().toString();
        String className = edtClassName.getText().toString();
        String NoS = edtNoS.getText().toString();
        String facultyName = edtFacultyName.getText().toString();


        if(TextUtils.isEmpty(No) || TextUtils.isEmpty(className) || TextUtils.isEmpty(NoS) || TextUtils.isEmpty(facultyName)) {
            Toast.makeText(ClassUpdateAdmin.this, "Empty fields!", Toast.LENGTH_SHORT).show();
            return;
        }
        int finalNoS = Integer.parseInt(NoS);
        int finalNo = Integer.parseInt(No);

        mData = FirebaseDatabase.getInstance().getReference();
        HashMap hashMap= new HashMap();
        hashMap.put("classID", finalNo);
        hashMap.put("className", className);
        hashMap.put("nos", finalNoS);
        hashMap.put("facultyName", facultyName);

        mData.child("class").child(No).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(ClassUpdateAdmin.this, "Update successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(this, "Successfully Update!", Toast.LENGTH_SHORT).show();
        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();


    }
}