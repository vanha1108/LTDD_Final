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
import com.laptrinhdidong.electroniccomunications.model.Tuition;

import java.util.HashMap;

public class TuitionUpdateAdmin extends AppCompatActivity {

    EditText edtSubject, edtNoC, edtPrice;
    Button btnSave, btnClose;
    DatabaseReference mData;
    Tuition newTuition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuition_update_admin);

        // anh xa
        edtSubject = findViewById(R.id.edtEditSubjectTuition);
        edtNoC = findViewById(R.id.edtEditNoCTuition);
        edtPrice = findViewById(R.id.edtEditPriceTuition);
        btnSave = findViewById(R.id.btnSaveEditTuition);
        btnClose = findViewById(R.id.edtCloseEditTuition);

        newTuition = (Tuition) getIntent().getExtras().get("object_tuition");

        if (newTuition != null) {
            edtSubject.setText(newTuition.getSubject());
            edtNoC.setText(Integer.toString(newTuition.getNoC()));
            edtPrice.setText(Integer.toString(newTuition.getPrice()));
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTuition();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void updateTuition() {
        String subject = edtSubject.getText().toString();

        String NoC = edtNoC.getText().toString();
        String price = edtPrice.getText().toString();


        if(TextUtils.isEmpty(subject) || TextUtils.isEmpty(NoC) || TextUtils.isEmpty(price)) {
            Toast.makeText(TuitionUpdateAdmin.this, "Empty fields!", Toast.LENGTH_SHORT).show();
            return;
        }
        int finalNoC = Integer.parseInt(NoC);
        int finalPrice = Integer.parseInt(price);

        mData = FirebaseDatabase.getInstance().getReference();
        HashMap hashMap= new HashMap();
        hashMap.put("subject", subject);
        hashMap.put("noC", finalNoC);
        hashMap.put("price", finalPrice);

        mData.child("tuition").child(subject).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(TuitionUpdateAdmin.this, "Update successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(this, "Successfully Update!", Toast.LENGTH_SHORT).show();
        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }
}