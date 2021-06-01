package com.laptrinhdidong.electroniccomunications.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.model.Teacher;
import com.laptrinhdidong.electroniccomunications.model.Tuition;

import java.util.ArrayList;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.MyViewHolder> {

    Context context;
    ArrayList<Teacher> teachers;

    public TeacherAdapter(Context context, ArrayList<Teacher> teachers) {
        this.context = context;
        this.teachers = teachers;
    }

    public void setData (ArrayList<Teacher> mListTeachers) {
        teachers = mListTeachers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.teacher_admin_itemview, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Teacher teacher = teachers.get(position);

        holder.myText1.setText("First Name: " + teacher.getFirstName());
        holder.myText2.setText("Last Name: " + teacher.getLastName());
        holder.myText3.setText("Date of Birth: " + teacher.getDoB());
        holder.myText4.setText("Sex: " + teacher.getSex());
        holder.myText5.setText("Age: " + teacher.getAge());
        holder.myText6.setText("Phone: " + teacher.getPhone());
        holder.myText7.setText("Gmail: " + teacher.getGmail());
        holder.myText8.setText("Class ID: " + teacher.getClassID());
        holder.myText9.setText("Faculty: " + teacher.getFacultyID());
    }

    @Override
    public int getItemCount()
    {
        if (teachers != null) {
            return teachers.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2, myText3, myText4, myText5, myText6, myText7, myText8, myText9;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.txtFirstNameTeacher);
            myText2 = itemView.findViewById(R.id.txtLastNameTeacher);
            myText3 = itemView.findViewById(R.id.textDateOfBirth);
            myText4 = itemView.findViewById(R.id.txtSex);
            myText5 = itemView.findViewById(R.id.txtAge);
            myText6 = itemView.findViewById(R.id.txtPhone);
            myText7 = itemView.findViewById(R.id.txtGmail);
            myText8 = itemView.findViewById(R.id.txtClassID);
            myText9 = itemView.findViewById(R.id.txtFaculty);

        }
    }

}
