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

import com.example.displayfinalproject.Adapter.TuitionAdminAdapter;
import com.laptrinhdidong.electroniccomunications.R;


import java.util.ArrayList;

public class TuitionFragmentAdmin extends Fragment {

    private Spinner spinnerFacultyTuitionAdmin, spinnerClassesTuitionAdmin, spinnerStudentTuitionAdmin;
    private RecyclerView recyclerView;
    String s1[], s2[], s3[];

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
        ArrayList<String> arrayFaculty = new ArrayList<String>();
        ArrayList<String> arrayClass = new ArrayList<String>();
        ArrayList<String> arrayStudent = new ArrayList<String>();

        arrayFaculty.add("Information Technology");
        arrayFaculty.add("Computer Engineering");

        arrayClass.add("17110CLS2");
        arrayClass.add("17110CLS3");

        arrayStudent.add("17110125");
        arrayStudent.add("17110126");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayFaculty);
        spinnerFacultyTuitionAdmin.setAdapter(arrayAdapter);

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayClass);
        spinnerClassesTuitionAdmin.setAdapter(arrayAdapter2);

        ArrayAdapter arrayAdapter3 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayStudent);
        spinnerStudentTuitionAdmin.setAdapter(arrayAdapter3);

        recyclerView = view.findViewById(R.id.fragmentTuitionRecyclerView);

        s1 = getResources().getStringArray(R.array.tuitionSubjectAdmin);
        s2 = getResources().getStringArray(R.array.tuitionNoCAdmin);
        s3 = getResources().getStringArray(R.array.tuitionPriceAdmin);

        TuitionAdminAdapter myAdapter = new TuitionAdminAdapter(getContext(), s1, s2, s3);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}