package fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.displayfinalproject.Adapter.TeacherAdminAdapter;
import com.laptrinhdidong.electroniccomunications.R;


import java.util.ArrayList;

public class TeacherFragmentAdmin extends Fragment {

    private Spinner spinnerFacultyTeacherAdmin;
    private RecyclerView recyclerView;
    String s1[], s2[], s3[], s4[], s5[];

    public TeacherFragmentAdmin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_admin, container, false);
        // Inflate the layout for this fragment
        spinnerFacultyTeacherAdmin = (Spinner)view.findViewById(R.id.spinnerTeacherFaculty);
        ArrayList<String> arrayClassFaculty = new ArrayList<String>();
        arrayClassFaculty.add("Information Technology");
        arrayClassFaculty.add("Computer Engineering");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayClassFaculty);
        spinnerFacultyTeacherAdmin.setAdapter(arrayAdapter);

        recyclerView = view.findViewById(R.id.teacherAdminRecyclerView);

        s1 = getResources().getStringArray(R.array.teacherNameAdmin);
        s2 = getResources().getStringArray(R.array.teacherDateAdmin);
        s3 = getResources().getStringArray(R.array.teacherSexAdmin);
        s4 = getResources().getStringArray(R.array.teacherPhoneAdmin);
        s5 = getResources().getStringArray(R.array.teacherMailAdmin);
        TeacherAdminAdapter myAdapter = new TeacherAdminAdapter(getContext(), s1, s2, s3, s4, s5);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}