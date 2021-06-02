package com.laptrinhdidong.electroniccomunications.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import com.laptrinhdidong.electroniccomunications.adapter.FacultyAdapter;
import com.laptrinhdidong.electroniccomunications.model.FacultyEntity;

import java.util.ArrayList;
import java.util.List;

public class FacultyAdminFragment extends Fragment {
    private Dialog dialog;
    private FloatingActionButton btnShowDialog;
    private Button btnAdd, btnClose;
    private EditText edtNameFaculty, edtNameDean, edtAddressDean, edtNameAssistant, edtAddressAssistant;

    private List<FacultyEntity> facultys = new ArrayList<>();
    private FacultyAdapter facultyAdapter;
    private RecyclerView recyclerFaculty;

    //private String fulName = "";
    private String facultyKey = "";

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseRoot = firebaseDatabase.getReference();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData(databaseRoot);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_faculty);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        btnShowDialog = view.findViewById(R.id.btnShowDialogAddFaculty);
        btnAdd = dialog.findViewById(R.id.btnAddFaculty);
        btnClose = dialog.findViewById(R.id.btnCloseFaculty);

        edtNameFaculty = dialog.findViewById(R.id.edtAddFacultyName);
        edtNameDean = dialog.findViewById(R.id.edtAddDeanName);
        edtAddressDean = dialog.findViewById(R.id.edtAddAddressDean);
        edtNameAssistant = dialog.findViewById(R.id.edtAddAssistantName);
        edtAddressAssistant = dialog.findViewById(R.id.edtAddAddressAssistant);

        recyclerFaculty = view.findViewById(R.id.recyclerFaculty);

        facultyAdapter = new FacultyAdapter(facultys, getContext(), new FacultyAdapter.HolderLongClick() {
            @Override
            public void showDialog(FacultyEntity facultyEntity) {
                //showDialog(facultyEntity);
                showDialogUpdateAndDelete(facultyEntity);

            }
        });

        btnShowDialog.setOnClickListener(v -> {
            dialog.show();
        });

        btnClose.setOnClickListener(v -> {
            dialog.dismiss();
        });

        btnAdd.setOnClickListener(v -> {
            String nameFaculty = edtNameFaculty.getText().toString().trim();
            String nameDean = edtNameDean.getText().toString().trim();
            String nameAssistant = edtNameAssistant.getText().toString().trim();
            String addressDean = edtAddressDean.getText().toString().trim();
            String addressAssistant = edtAddressAssistant.getText().toString().trim();

            if (nameFaculty.equals("") || nameDean.equals("") || nameAssistant.equals("")
                    || addressDean.equals("") || addressAssistant.equals("")) {
                Toast.makeText(getContext(), "Please fulfill Faculty Information!!", Toast.LENGTH_LONG).show();
                return;
            }
            try {
                FacultyEntity faculty = new FacultyEntity(facultyKey, nameFaculty, nameDean, addressDean, nameAssistant, addressAssistant);
                handlerFaculty(faculty);
                loadData(databaseRoot);
                facultyAdapter.notifyDataSetChanged();

                edtNameFaculty.setText("");
                edtNameDean.setText("");
                edtNameAssistant.setText("");
                edtAddressAssistant.setText("");
                edtAddressDean.setText("");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
        });
        return view;
    }

    private void handlerFaculty(FacultyEntity facultyEntity) {
        if (facultyEntity.getKey() == "") {
            databaseRoot.child("Faculty").push().setValue(facultyEntity);
            Toast.makeText(getContext(), "Add Faculty successful", Toast.LENGTH_LONG).show();
        } else {
            databaseRoot.child("Faculty").child(facultyEntity.getKey()).setValue(facultyEntity);
            facultyKey = "";
            Toast.makeText(getContext(), "Update Faculty successful", Toast.LENGTH_LONG).show();
        }
    }

    public void loadData(DatabaseReference databaseRoot) {
        databaseRoot.child("Faculty").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                facultys.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String key = dataSnapshot.getKey();
                    String facultyName = dataSnapshot.child("facultyName").getValue(String.class);
                    String deanName = dataSnapshot.child("deanName").getValue(String.class);
                    String deanAddress = dataSnapshot.child("addressDean").getValue(String.class);
                    String assistantName = dataSnapshot.child("assistantName").getValue(String.class);
                    String addressAssistant = dataSnapshot.child("addressAssistant").getValue(String.class);

                    try {
                        if (key != "" && facultyName != "" && deanName != "" && deanAddress != "" && assistantName != "" && addressAssistant != "") {
                            FacultyEntity facultyEntity = new FacultyEntity(key, facultyName, deanName, deanAddress, assistantName, addressAssistant);
                            facultys.add(facultyEntity);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                //load recyclerview
                facultyAdapter.setData(facultys);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                recyclerFaculty.setLayoutManager(linearLayoutManager);
                recyclerFaculty.setAdapter(facultyAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void showDialogUpdateAndDelete(FacultyEntity facultyEntity){
        Dialog dialogOption = new Dialog(getContext());
        dialogOption.setTitle("");
        dialogOption.setCancelable(true);
        dialogOption.setContentView(R.layout.dialog_opitons);
        dialogOption.show();

        TextView txtUpdate = dialogOption.findViewById(R.id.txtUpdateOption);
        TextView txtDelete = dialogOption.findViewById(R.id.txtDeleteOption);


        txtUpdate.setOnClickListener(v -> {
            //set data
            edtNameFaculty.setText(facultyEntity.getFacultyName());
            edtNameDean.setText(facultyEntity.getDeanName());
            edtAddressDean.setText(facultyEntity.getAddressDean());
            edtNameAssistant.setText(facultyEntity.getAssistantName());
            edtAddressAssistant.setText(facultyEntity.getAddressAssistant());
            facultyKey = facultyEntity.getKey();

            dialog.show();

            dialogOption.dismiss();
        });

        txtDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Confirm");
            builder.setMessage("Are you sure you want to delete Faculty " + facultyEntity.getFacultyName() + " ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    databaseRoot.child("Faculty").child(facultyEntity.getKey()).removeValue();
                    Toast.makeText(getContext(), "Delete Faculty " + facultyEntity.getFacultyName()
                            + " successful", Toast.LENGTH_LONG).show();
                    reload(facultyEntity.getFacultyName(),databaseRoot);
                }
            });

            builder.setNegativeButton("No", null);
            builder.show();

            dialogOption.dismiss();
        });
    }

    private void reload(String facultyName, DatabaseReference databaseRoot) {
        databaseRoot.child("Faculty").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                facultys.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String key = dataSnapshot.getKey();
                    String facultyName = dataSnapshot.child("facultyName").getValue(String.class);
                    String deanName = dataSnapshot.child("deanName").getValue(String.class);
                    String deanAddress = dataSnapshot.child("addressDean").getValue(String.class);
                    String assistantName = dataSnapshot.child("assistantName").getValue(String.class);
                    String addressAssistant = dataSnapshot.child("addressAssistant").getValue(String.class);

                    try {
                        if (facultyName != "" && deanName != "" && deanAddress != "" &&
                                assistantName != "" && addressAssistant != "") {
                            FacultyEntity facultyEntity = new FacultyEntity(key, facultyName, deanName, deanAddress, assistantName, addressAssistant);
                            facultys.add(facultyEntity);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                facultyAdapter.setData(facultys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
