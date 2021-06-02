package com.laptrinhdidong.electroniccomunications.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.laptrinhdidong.electroniccomunications.adapter.TimeTableAdapter;
import com.laptrinhdidong.electroniccomunications.model.TimeTableEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimeTableFragment extends Fragment {

    private Dialog dialog;
    private FloatingActionButton btnShowDialog;
    private TextView txtDate;
    private Spinner spinnerFacultyDialog, spinnerClassDialog, spinnerTeacherDialog, spinnerSubjectDialog,
            spinnerStartDialog, spinnerNumberDialog, spinnerFaculty, spinnerClass, spinnerRoomDialog;
    private Button btnSave, btnClose;
    private ImageButton btnShowCalender;
    private String[] lessons, numberLessons, rooms;
    private ArrayAdapter<String> adapterLessons, adapterNumberLesson, adapterRoom, adapterFalcuty, adapterClass, adapterTeacher, adapterSubject;

    private List<String> facultyNames = new ArrayList<>();
    private List<String> classNames = new ArrayList<>();
    private List<String> teacherNames = new ArrayList<>();
    private List<String> subjectNames = new ArrayList<>();

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseRoot = firebaseDatabase.getReference();

    private String facultyName = "";
    private String className = "";
    private String timeTableKey = "";

    private Calendar calendar;
    private SimpleDateFormat spdf = new SimpleDateFormat("dd/MM/yyyy");

    private List<TimeTableEntity> timeTables = new ArrayList<>();
    private TimeTableAdapter timeTableAdapter;
    private RecyclerView recyclerTimeTable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData(databaseRoot);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_table, container, false);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_time_table);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        btnShowDialog = view.findViewById(R.id.btnShowDialogAddTimeTable);
        btnSave = dialog.findViewById(R.id.btnSaveTimeTable);
        btnClose = dialog.findViewById(R.id.btnCloseDialogTimeTable);
        btnShowCalender = dialog.findViewById(R.id.btnShowCalenderTimeTable);
        txtDate = dialog.findViewById(R.id.txtDateDialogTimeTable);
        spinnerFacultyDialog = dialog.findViewById(R.id.spinnerFacultyDialogTimeTable);
        spinnerClassDialog = dialog.findViewById(R.id.spinnerClassDialogTimeTable);
        spinnerTeacherDialog = dialog.findViewById(R.id.spinnerTeacherDialogTimeTable);
        spinnerSubjectDialog = dialog.findViewById(R.id.spinnerSubjectNameDialogTimeTable);
        spinnerStartDialog = dialog.findViewById(R.id.spinnerStartDialogTimeTable);
        spinnerNumberDialog = dialog.findViewById(R.id.spinnerNumberOfLesson);
        spinnerRoomDialog = dialog.findViewById(R.id.spinnerRoomNameDialogTimeTable);

        spinnerFaculty = view.findViewById(R.id.spinner_faculty_add_time_table);
        spinnerClass = view.findViewById(R.id.spinner_class_add_time_table);
        recyclerTimeTable = view.findViewById(R.id.recyclerAddTimeTable);

        // Load recyclerView
        timeTableAdapter = new TimeTableAdapter(timeTables, getContext(), new TimeTableAdapter.HolderLongClick() {
            @Override
            public void showDialog(TimeTableEntity timeTableEntity) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerTimeTable.setLayoutManager(linearLayoutManager);
        recyclerTimeTable.setAdapter(timeTableAdapter);

        calendar = Calendar.getInstance();
        txtDate.setText(spdf.format(calendar.getTime()));

        // Load spinnerStart
        lessons = getResources().getStringArray(R.array.lessons);
        adapterLessons = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, lessons);
        adapterLessons.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerStartDialog.setAdapter(adapterLessons);

        // Load spinnerNumber
        numberLessons = getResources().getStringArray(R.array.numberLesson);
        adapterNumberLesson = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, numberLessons);
        adapterNumberLesson.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerNumberDialog.setAdapter(adapterNumberLesson);

        // Load spinnerRoomDialog
        rooms = getResources().getStringArray(R.array.rooms);
        adapterRoom = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, rooms);
        adapterRoom.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerRoomDialog.setAdapter(adapterRoom);

        btnShowDialog.setOnClickListener(v -> {
            dialog.show();
        });

        btnClose.setOnClickListener(v -> {
            dialog.dismiss();
        });

        btnShowCalender.setOnClickListener(v -> {
            DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    txtDate.setText(spdf.format(calendar.getTime()));
                }
            };
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    callBack,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH), calendar.DAY_OF_MONTH);
            datePickerDialog.show();
        });

        btnSave.setOnClickListener(v -> {
            if (facultyNames != null && facultyNames.size() > 0 && classNames != null && classNames.size() > 0
                    && teacherNames != null && teacherNames.size() > 0 && subjectNames != null && subjectNames.size() > 0
                    && lessons != null && lessons.length > 0 && numberLessons != null && numberLessons.length > 0
                    && rooms != null && rooms.length > 0) {
                try {
                    String date = txtDate.getText().toString();
                    String faculty = spinnerFacultyDialog.getSelectedItem().toString();
                    String nClass = spinnerClassDialog.getSelectedItem().toString();
                    String nTeacher = spinnerTeacherDialog.getSelectedItem().toString();
                    String nSubject = spinnerSubjectDialog.getSelectedItem().toString();
                    String nRoom = spinnerRoomDialog.getSelectedItem().toString();
                    String sStart = spinnerStartDialog.getSelectedItem().toString();
                    String sNumber = spinnerNumberDialog.getSelectedItem().toString();
                    int start = Integer.parseInt(sStart);
                    int number = Integer.parseInt(sNumber);

                    if (date.equals("") || faculty.equals("") || nClass.equals("") || nTeacher.equals("")
                            || nSubject.equals("") || sStart.equals("") || sNumber.equals("")) {
                        Toast.makeText(getContext(), "Please enter the full information", Toast.LENGTH_LONG).show();
                        return;
                    }

                    getAllTimeTable();
                    for (TimeTableEntity timeTable : timeTables) {
                        int s1 = Integer.parseInt(timeTable.getStartLesson());
                        int f1 = s1 + Integer.parseInt(timeTable.getNumberLesson());
                        boolean check = checkDupLesson(s1, f1, start, start + number);
                        if (timeTable.getDate().equals(date)) {
                            if (check) {
                                if (timeTable.getClassName().equals(nClass)) {
                                    Toast.makeText(getContext(), "Invalid study time", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                if (timeTable.getTeacherName().equals(nTeacher)) {
                                    Toast.makeText(getContext(), "Invalid study teacher", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                if (timeTable.getRoomName().equals(nRoom) && check) {
                                    Toast.makeText(getContext(), "Invalid study room", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        }
                    }
                    TimeTableEntity timeTableEntity = new TimeTableEntity(nClass, nTeacher, nSubject, nRoom, sStart, sNumber, date);
                    handlerSaveTimeTable(timeTableKey, timeTableEntity);
                    reload(spinnerClass.getSelectedItem().toString(), databaseRoot);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        spinnerFaculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = spinnerFaculty.getSelectedItem().toString();
                reloadAll(databaseRoot);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    public boolean checkDupLesson(int min1, int max1, int min2, int max2) {
        if (max2 <= min1) {
            return false;
        } else if (max1 <= min2) {
            return false;
        } else {
            return true;
        }
    }

    public void handlerSaveTimeTable(String key, TimeTableEntity timeTableEntity) {
        if (key == "") {
            databaseRoot.child("timeTable").push().setValue(timeTableEntity);
            Toast.makeText(getContext(), "Add timetable successfull", Toast.LENGTH_LONG).show();
        } else {
            databaseRoot.child("timeTable").child(key).setValue(timeTableEntity);
            timeTableKey = "";
            Toast.makeText(getContext(), "Update subject successfull", Toast.LENGTH_LONG).show();
        }
    }

    public void getAllTimeTable() {
        databaseRoot.child("TimeTable").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                timeTables.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String key = dataSnapshot.getKey();
                    String className = dataSnapshot.child("className").getValue(String.class);
                    String teacherName = dataSnapshot.child("teacherName").getValue(String.class);
                    String subjectName = dataSnapshot.child("subjectName").getValue(String.class);
                    String roomName = dataSnapshot.child("roomName").getValue(String.class);
                    String sStartLesson = dataSnapshot.child("startLesson").getValue(String.class);
                    String sNumberLesson = dataSnapshot.child("numberLesson").getValue(String.class);
                    try {
                        if (key != "" && !className.equals("") && !teacherName.equals("") && !subjectName.equals("") && !roomName.equals("")
                                && !sStartLesson.equals("") && !sNumberLesson.equals("")) {
                            TimeTableEntity timeTableEntity = new TimeTableEntity(key, className, teacherName, subjectName, roomName, sStartLesson, sNumberLesson);
                            timeTables.add(timeTableEntity);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void loadData(DatabaseReference databaseRoot) {
        databaseRoot.child("Faculty").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                facultyNames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String falName = dataSnapshot.child("facultyName").getValue(String.class);
                    if (falName != "") {
                        facultyNames.add(falName);
                    }
                }
                adapterFalcuty = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, facultyNames);
                adapterFalcuty.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerFaculty.setAdapter(adapterFalcuty);
                spinnerFacultyDialog.setAdapter(adapterFalcuty);
                if (facultyNames != null && facultyNames.size() > 0) {
                    facultyName = spinnerFaculty.getSelectedItem().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseRoot.child("class").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                classNames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String clName = dataSnapshot.child("className").getValue(String.class);
                    String checkFacultyName = dataSnapshot.child("facultyName").getValue(String.class);
                    if (clName != "" && facultyName.equals(checkFacultyName)) {
                        classNames.add(clName);
                    }
                }
                adapterClass = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, classNames);
                adapterClass.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerClass.setAdapter(adapterClass);
                spinnerClassDialog.setAdapter(adapterClass);
                if (classNames != null && classNames.size() > 0) {
                    className = spinnerClass.getSelectedItem().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseRoot.child("teacher").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                teacherNames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String firstName = dataSnapshot.child("firstName").getValue(String.class);
                    String lastName = dataSnapshot.child("lastName").getValue(String.class);
                    String falNameOfTeacher = dataSnapshot.child("facultyName").getValue(String.class);
                    String techName = firstName + " " + lastName;
                    if (techName != "" && facultyName.equals(falNameOfTeacher)) {
                        teacherNames.add(techName);
                    }
                }
                adapterTeacher = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, teacherNames);
                adapterTeacher.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerTeacherDialog.setAdapter(adapterTeacher);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseRoot.child("subject").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                subjectNames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String subName = dataSnapshot.child("name").getValue(String.class);
                    String falNameOfSubject = dataSnapshot.child("facultyName").getValue(String.class);
                    if (subName != "" && facultyName.equals(falNameOfSubject)) {
                        subjectNames.add(subName);
                    }
                }
                adapterSubject = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, subjectNames);
                adapterSubject.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerSubjectDialog.setAdapter(adapterSubject);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseRoot.child("timeTable").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                timeTables.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String key = dataSnapshot.getKey();
                    String nClass = dataSnapshot.child("className").getValue(String.class);
                    String nTeacher = dataSnapshot.child("teacherName").getValue(String.class);
                    String nSubject = dataSnapshot.child("subjectName").getValue(String.class);
                    String nRoom = dataSnapshot.child("roomName").getValue(String.class);
                    String sStart = dataSnapshot.child("startLesson").getValue(String.class);
                    String sNumber = dataSnapshot.child("numberLesson").getValue(String.class);
                    String sDate = dataSnapshot.child("date").getValue(String.class);
                    try {
                        if (!nClass.equals("") && !nTeacher.equals("") && !nSubject.equals("")
                                && !nRoom.equals("") && !sStart.equals("") && !sNumber.equals("") && className.equals(nClass)) {
                            TimeTableEntity timeTableEntity = new TimeTableEntity(key, nClass, nTeacher, nSubject, nRoom, sStart, sNumber, sDate);
                            timeTables.add(timeTableEntity);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // Reload timeTableAdapter
                if (timeTableAdapter != null) {
                    {
                        timeTableAdapter.setData(timeTables);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void reload(String className, DatabaseReference databaseRoot) {
        databaseRoot.child("timeTable").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                timeTables.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String key = dataSnapshot.getKey();
                    String nClass = dataSnapshot.child("className").getValue(String.class);
                    String nTeacher = dataSnapshot.child("teacherName").getValue(String.class);
                    String nSubject = dataSnapshot.child("subjectName").getValue(String.class);
                    String nRoom = dataSnapshot.child("roomName").getValue(String.class);
                    String sStart = dataSnapshot.child("startLesson").getValue(String.class);
                    String sNumber = dataSnapshot.child("numberLesson").getValue(String.class);
                    String sDate = dataSnapshot.child("date").getValue(String.class);
                    try {
                        if (!nClass.equals("") && !nTeacher.equals("") && !nSubject.equals("")
                                && !nRoom.equals("") && !sStart.equals("") && !sNumber.equals("") && className.equals(nClass)) {
                            TimeTableEntity timeTableEntity = new TimeTableEntity(key, nClass, nTeacher, nSubject, nRoom, sStart, sNumber, sDate);
                            timeTables.add(timeTableEntity);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // timeTableAdapter.setData
                timeTableAdapter.setData(timeTables);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void reloadAll(DatabaseReference databaseRoot) {
        facultyName = "";
        className = "";
        facultyName = spinnerFaculty.getSelectedItem().toString();
        databaseRoot.child("class").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                classNames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String clName = dataSnapshot.child("className").getValue(String.class);
                    String checkFacultyName = dataSnapshot.child("facultyName").getValue(String.class);
                    if (clName != "" && facultyName.equals(checkFacultyName)) {
                        classNames.add(clName);
                    }
                }
                if (adapterClass != null) {
                    adapterClass.notifyDataSetChanged();
                    if (classNames != null && classNames.size() > 0) {
                        className = classNames.get(0);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseRoot.child("teacher").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                teacherNames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String firstName = dataSnapshot.child("firstName").getValue(String.class);
                    String lastName = dataSnapshot.child("lastName").getValue(String.class);
                    String falNameOfTeacher = dataSnapshot.child("facultyName").getValue(String.class);
                    String techName = firstName + " " + lastName;
                    if (techName != "" && facultyName.equals(falNameOfTeacher)) {
                        teacherNames.add(techName);
                    }
                }
                adapterTeacher = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, teacherNames);
                adapterTeacher.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerTeacherDialog.setAdapter(adapterTeacher);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseRoot.child("subject").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                subjectNames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String subName = dataSnapshot.child("name").getValue(String.class);
                    String falNameOfSubject = dataSnapshot.child("facultyName").getValue(String.class);
                    if (subName != "" && falNameOfSubject.equals(facultyName)) {
                        subjectNames.add(subName);
                    }
                }
                adapterSubject = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, subjectNames);
                adapterSubject.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerSubjectDialog.setAdapter(adapterSubject);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseRoot.child("timeTable").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                timeTables.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String key = dataSnapshot.getKey();
                    String nClass = dataSnapshot.child("className").getValue(String.class);
                    String nTeacher = dataSnapshot.child("teacherName").getValue(String.class);
                    String nSubject = dataSnapshot.child("subjectName").getValue(String.class);
                    String nRoom = dataSnapshot.child("roomName").getValue(String.class);
                    String sStart = dataSnapshot.child("startLesson").getValue(String.class);
                    String sNumber = dataSnapshot.child("numberLesson").getValue(String.class);
                    String sDate = dataSnapshot.child("date").getValue(String.class);
                    try {
                        if (!nClass.equals("") && !nTeacher.equals("") && !nSubject.equals("")
                                && !nRoom.equals("") && !sStart.equals("") && !sNumber.equals("") && className.equals(nClass)) {
                            TimeTableEntity timeTableEntity = new TimeTableEntity(key, nClass, nTeacher, nSubject, nRoom, sStart, sNumber, sDate);
                            timeTables.add(timeTableEntity);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // Reload timeTableAdapter
                if (timeTableAdapter != null) {
                    {
                        timeTableAdapter.setData(timeTables);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}