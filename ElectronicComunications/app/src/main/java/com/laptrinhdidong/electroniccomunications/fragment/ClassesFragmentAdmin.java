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
import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.activity.ClassUpdateAdmin;
import com.laptrinhdidong.electroniccomunications.adapter.ClassesAdminAdapter;
import com.laptrinhdidong.electroniccomunications.model.Classes;
import com.laptrinhdidong.electroniccomunications.model.Teacher;

import java.util.ArrayList;

public class ClassesFragmentAdmin extends Fragment {

    private Dialog dialog;
    private Spinner spinnerClassesAdmin;
    private RecyclerView recyclerView;
    private ClassesAdminAdapter myAdapter;
    private static final int MY_REQUEST_CODE = 10;
    FloatingActionButton fltAddClassAdmin;
    Button btnAddClass, btnCloseClass;
    EditText edtAddClass, edtAddClassName, edtAddClassNoS, edtAddClassFacultyName;
    DatabaseReference mData;
    ArrayList<Classes> newClasses;


    public ClassesFragmentAdmin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classes_admin, container, false);


        //spinner
        spinnerClassesAdmin = (Spinner)view.findViewById(R.id.spinnerAddClass);
        ArrayList<String> arrayClassFaculty = new ArrayList<String>();
        arrayClassFaculty.add("Information Technology");
        arrayClassFaculty.add("Computer Engineering");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayClassFaculty);
        spinnerClassesAdmin.setAdapter(arrayAdapter);

        // recyclerview
        mData= FirebaseDatabase.getInstance().getReference();
        recyclerView = view.findViewById(R.id.classAdminRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newClasses = new  ArrayList<>();
        myAdapter = new ClassesAdminAdapter(getContext(), newClasses, new ClassesAdminAdapter.OnLongClick() {
            @Override
            public void showDialogUpdateDelete(Classes newClass) {
                dialogUpdateDelete(newClass);
            }
        });
        recyclerView.setAdapter(myAdapter);

        mData.child("class").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Classes newClass2 = dataSnapshot.getValue(Classes.class);
                    newClasses.add(newClass2);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_class);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        // anh xa
        fltAddClassAdmin = view.findViewById(R.id.btnShowDialogAddClass);
        btnAddClass = dialog.findViewById(R.id.btnAddClass);
        edtAddClass = dialog.findViewById(R.id.edtAddClassID);
        edtAddClassName = dialog.findViewById(R.id.edtAddClassName);
        edtAddClassNoS = dialog.findViewById(R.id.edtAddClassNoS);
        btnCloseClass = dialog.findViewById(R.id.btnCloseClass);
        edtAddClassFacultyName = dialog.findViewById(R.id.edtAddClassFacultyName);


        fltAddClassAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtAddClass.getText().toString().equals("") || edtAddClassName.getText().toString().equals("")
                        || edtAddClassNoS.getText().toString().equals("") || edtAddClassFacultyName.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Empty fields!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Classes newClass = new Classes(Integer.parseInt(edtAddClass.getText().toString()), edtAddClassName.getText().toString().trim(),
                        Integer.parseInt(edtAddClassNoS.getText().toString()), edtAddClassFacultyName.getText().toString().trim());
                mData.child("class").child(edtAddClass.getText().toString()).setValue(newClass);
                Toast.makeText(getContext(), "Add class successfully!", Toast.LENGTH_SHORT).show();
                loadData();
            }
        });

        btnCloseClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return view;
    }

    private void dialogUpdateDelete(Classes newClass) {
        Dialog dialog = new Dialog(getContext());
        dialog.setTitle("");
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_update_delete_class);
        dialog.show();

        TextView dialogUpdateClass = (TextView)dialog.findViewById(R.id.txtUpdateDialogClass);
        TextView dialogDeleteClass = (TextView)dialog.findViewById(R.id.txtDeleteDialogClass);

        dialogUpdateClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ClassUpdateAdmin.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_class", newClass);
                intent.putExtras(bundle);
                startActivityForResult(intent, MY_REQUEST_CODE);
            }
        });

        dialogDeleteClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.child("class").child(newClass.getClassID().toString()).setValue(null);
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
        ArrayList<Classes> class2 = new ArrayList<>();
        mData.child("class").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Classes newClass = dataSnapshot.getValue(Classes.class);
                    class2.add(newClass);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myAdapter.setData(class2);
    }

}