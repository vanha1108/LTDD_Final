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
import com.laptrinhdidong.electroniccomunications.activity.TuitionUpdateAdmin;
import com.laptrinhdidong.electroniccomunications.adapter.TuitionAdminAdapter;
import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.model.Classes;
import com.laptrinhdidong.electroniccomunications.model.Tuition;



import java.util.ArrayList;

public class TuitionFragmentAdmin extends Fragment {

    private Spinner spinnerFacultyTuitionAdmin, spinnerClassesTuitionAdmin, spinnerStudentTuitionAdmin;
    private RecyclerView recyclerView;
    String s1[], s2[], s3[];
    private Dialog dialog;
    EditText edtSubject, edtNoC, edtPrice;
    Button btnAdd, btnClose;
    FloatingActionButton fltAdd;
    DatabaseReference mData;
    DatabaseReference mData2;
    ArrayList<Tuition> tuitions;
    TuitionAdminAdapter myAdapter;
    private static final int MY_REQUEST_CODE = 10;


    public TuitionFragmentAdmin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tuition_admin, container, false);
        spinnerFacultyTuitionAdmin = (Spinner)view.findViewById(R.id.spinnerTuitionFaculty);
        spinnerClassesTuitionAdmin = (Spinner)view.findViewById(R.id.spinnerTuitionClasses);
        spinnerStudentTuitionAdmin = (Spinner)view.findViewById(R.id.spinnerTuitionStudent);

        // spinner
        ArrayList<String> arrayClass2 = new ArrayList<String>();

        ArrayList<String> arrayFaculty = new ArrayList<String>();
        ArrayList<String> arrayClass = new ArrayList<String>();
        ArrayList<String> arrayStudent = new ArrayList<String>();

        arrayFaculty.add("Information Technology");
        arrayFaculty.add("Computer Engineering");

        arrayClass.add("17110CLS2");
        arrayClass.add("17110CLS3");

        arrayStudent.add("17110125");
        arrayStudent.add("17110126");

        mData2 = FirebaseDatabase.getInstance().getReference();
        mData2.child("class").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Classes newClass = dataSnapshot.getValue(Classes.class);

                    arrayClass2.add(newClass.getClassName());
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayFaculty);
        spinnerFacultyTuitionAdmin.setAdapter(arrayAdapter);

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayClass2);
        spinnerClassesTuitionAdmin.setAdapter(arrayAdapter2);

        ArrayAdapter arrayAdapter3 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayStudent);
        spinnerStudentTuitionAdmin.setAdapter(arrayAdapter3);

        // dialog add
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_tuition);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        // anh xa
        fltAdd = view.findViewById(R.id.btnShowDialogAddTuition);
        edtSubject = dialog.findViewById(R.id.edtAddSubjectTuition);
        edtNoC = dialog.findViewById(R.id.edtAddNoCTuition);
        edtPrice = dialog.findViewById(R.id.edtAddPriceTuition);
        btnAdd = dialog.findViewById(R.id.btnAddTuition);
        btnClose = dialog.findViewById(R.id.btnCloseTuition);

        mData= FirebaseDatabase.getInstance().getReference();
        // xu xy button
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtSubject.getText().toString().equals("") || edtNoC.getText().toString().equals("") || edtPrice.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Empty fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                Tuition tuition = new Tuition(edtSubject.getText().toString().trim(), Integer.parseInt(edtNoC.getText().toString()), Integer.parseInt(edtPrice.getText().toString()));
                mData.child("tuition").child(edtSubject.getText().toString()).setValue(tuition);
                Toast.makeText(getContext(), "Add tuition successfully!", Toast.LENGTH_SHORT).show();

                ArrayList<Tuition> tuition2 = new ArrayList<>();
                mData.child("tuition").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Tuition newTuition = dataSnapshot.getValue(Tuition.class);
                            tuition2.add(newTuition);
                        }
                        myAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myAdapter.setData(tuition2);
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        // recyclerview
        recyclerView = view.findViewById(R.id.fragmentTuitionRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tuitions = new ArrayList<>();


        myAdapter = new TuitionAdminAdapter(getContext(), tuitions, new TuitionAdminAdapter.OnLongClick() {
            @Override
            public void showDialogUpdateDelete(Tuition tuition) {
                dialogUpdateDelete(tuition);
            }
        });
        recyclerView.setAdapter(myAdapter);
        mData.child("tuition").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Tuition newTuition = dataSnapshot.getValue(Tuition.class);
                    tuitions.add(newTuition);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    private void dialogUpdateDelete(Tuition newTuition) {
        Dialog dialog = new Dialog(getContext());
        dialog.setTitle("");
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_update_delete_tuition);
        dialog.show();

        TextView dialogUpdateTuition = (TextView)dialog.findViewById(R.id.txtUpdateDialogTuition);
        TextView dialogDeleteTuition = (TextView)dialog.findViewById(R.id.txtDeleteDialogTuition);

        dialogUpdateTuition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TuitionUpdateAdmin.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_tuition", newTuition);
                intent.putExtras(bundle);
                startActivityForResult(intent, MY_REQUEST_CODE);
            }
        });

        dialogDeleteTuition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.child("tuition").child(newTuition.getSubject().toString()).setValue(null);

                ArrayList<Tuition> tuition2 = new ArrayList<>();
                mData.child("tuition").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Tuition newTuition = dataSnapshot.getValue(Tuition.class);
                            tuition2.add(newTuition);
                        }
                        myAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myAdapter.setData(tuition2);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            ArrayList<Tuition> tuition2 = new ArrayList<>();
            mData.child("tuition").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Tuition newTuition = dataSnapshot.getValue(Tuition.class);
                        tuition2.add(newTuition);
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            myAdapter.setData(tuition2);
        }
    }
}