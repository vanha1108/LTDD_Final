package com.laptrinhdidong.electroniccomunications.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.adapter.FacultyAdapter;
import com.laptrinhdidong.electroniccomunications.model.FacultyEntity;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {

    private Dialog dialog;

    private Button btnAdd, btnClose;
    private EditText edtNameFaculty, edtNameDean, edtNameAssistant,
            edtAddressAssistant, edtAddressDean;
    private FloatingActionButton btnShow;

    DatabaseReference mData;
    private RecyclerView recyclerViewFaculty;
    private FacultyAdapter adapter ;
//    private List<FacultyEntity> mListFaculty;
//    private FacultyAdapter adapter ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_faculty);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        btnAdd = dialog.findViewById(R.id.btnAddFaculty);
        btnClose = dialog.findViewById(R.id.btnCloseFaculty);
        btnShow = view.findViewById(R.id.btnShowDialogAddFaculty);

        edtNameFaculty = dialog.findViewById(R.id.edtAddFacultyName);
        edtNameDean = dialog.findViewById(R.id.edtAddDeanName);
        edtAddressDean = dialog.findViewById(R.id.edtAddAddressDean);
        edtNameAssistant = dialog.findViewById(R.id.edtAddAssistantName);
        edtAddressAssistant = dialog.findViewById(R.id.edtAddAddressAssistant);

        mData = FirebaseDatabase.getInstance().getReference();
        recyclerViewFaculty = view.findViewById(R.id.recyclerFaculty);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        recyclerViewFaculty.setLayoutManager(linearLayoutManager);

        FirebaseRecyclerOptions<FacultyEntity> options =
                new FirebaseRecyclerOptions.Builder<FacultyEntity>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child("Faculty"), FacultyEntity.class)
                        .build();

        adapter = new FacultyAdapter(options);
        recyclerViewFaculty.setAdapter(adapter);

        //adapter = new FacultyAdapter();
//        adapter = new FacultyAdapter(new FacultyAdapter.OnLongClick(){
//            @Override
//            public void showDialogUpdateDelete(FacultyEntity facultyEntity){
//
//            }
//        });

        // load dữ liệu lên reclerview
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
//                RecyclerView.VERTICAL, false);
//        recyclerViewFaculty.setLayoutManager(linearLayoutManager);
//        adapter.setData(mListFaculty);
//        recyclerViewFaculty.setAdapter(adapter);




        btnShow.setOnClickListener(v -> {
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

//            mData = FirebaseDatabase.getInstance().getReference();
            FacultyEntity facultyEntity = new FacultyEntity(nameFaculty, nameDean, addressDean,
                    nameAssistant, addressAssistant);
            mData.child("Faculty").push().setValue(facultyEntity);

            Toast.makeText(getContext(), "Add Faculty successfully! ", Toast.LENGTH_LONG).show();

            edtNameFaculty.setText("");
            edtNameDean.setText("");
            edtNameAssistant.setText("");
            edtAddressAssistant.setText("");
            edtAddressDean.setText("");
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
