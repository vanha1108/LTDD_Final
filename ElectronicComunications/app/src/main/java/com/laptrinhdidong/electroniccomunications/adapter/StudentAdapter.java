package com.laptrinhdidong.electroniccomunications.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.model.Student;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    Context context;
    ArrayList<Student> students;

    public StudentAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
    }

    public void setData (ArrayList<Student> mListStudents) {
        students = mListStudents;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.student_admin_itemview, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student student = students.get(position);

        holder.myText1.setText("Student Code: " + student.getStudentCode());
        holder.myText2.setText("First Name: " + student.getFirstName());
        holder.myText3.setText("Last Name: " + student.getLastName());
        holder.myText4.setText("Date of Birth: " + student.getDoB());
        holder.myText5.setText("Sex: " + student.getSex());
        holder.myText6.setText("Age: " + student.getAge());
        holder.myText7.setText("Phone: " + student.getPhone());
        holder.myText8.setText("Gmail: " + student.getGmail());
        holder.myText9.setText("Class ID: " + student.getClassID());
        holder.myText10.setText("Faculty: " + student.getFacultyID());
    }

    @Override
    public int getItemCount()
    {
        if (students != null) {
            return students.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2, myText3, myText4, myText5, myText6, myText7, myText8, myText9, myText10;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.edtStudentCode);
            myText2 = itemView.findViewById(R.id.txtFirstNameStudent);
            myText3 = itemView.findViewById(R.id.txtLastNameStudent);
            myText4 = itemView.findViewById(R.id.textDateOfBirthStudent);
            myText5 = itemView.findViewById(R.id.txtSexStudent);
            myText6 = itemView.findViewById(R.id.txtAgeStudent);
            myText7 = itemView.findViewById(R.id.txtPhoneStudent);
            myText8 = itemView.findViewById(R.id.txtGmailStudent);
            myText9 = itemView.findViewById(R.id.txtClassIDStudent);
            myText10 = itemView.findViewById(R.id.txtFacultyStudent);

        }
    }

}
