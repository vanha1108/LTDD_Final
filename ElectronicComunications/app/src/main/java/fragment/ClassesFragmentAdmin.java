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

import com.laptrinhdidong.electroniccomunications.R;
import com.example.displayfinalproject.Adapter.ClassesAdminAdapter;

import java.util.ArrayList;

public class ClassesFragmentAdmin extends Fragment {

    private Spinner spinnerClassesAdmin;
    private RecyclerView recyclerView;
    String s1[], s2[], s3[];

    public ClassesFragmentAdmin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classes_admin, container, false);
        spinnerClassesAdmin = (Spinner)view.findViewById(R.id.spinnerClassesFaculty);
        ArrayList<String> arrayClassFaculty = new ArrayList<String>();
        arrayClassFaculty.add("Information Technology");
        arrayClassFaculty.add("Computer Engineering");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayClassFaculty);
        spinnerClassesAdmin.setAdapter(arrayAdapter);

        recyclerView = view.findViewById(R.id.classAdminRecyclerView);

        s1 = getResources().getStringArray(R.array.classAdminID);
        s2 = getResources().getStringArray(R.array.classNameAdmin);
        s3 = getResources().getStringArray(R.array.classAdminNoS);
        ClassesAdminAdapter myAdapter = new ClassesAdminAdapter(getContext(), s1, s2, s3);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}