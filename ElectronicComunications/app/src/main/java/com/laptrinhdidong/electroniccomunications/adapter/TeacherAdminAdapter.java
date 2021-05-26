package com.laptrinhdidong.electroniccomunications.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laptrinhdidong.electroniccomunications.R;

public class TeacherAdminAdapter extends RecyclerView.Adapter<TeacherAdminAdapter.MyViewHolder> {

    String data1[], data2[], data3[], data4[], data5[];

    Context context;

    public TeacherAdminAdapter(Context ct, String s1[], String s2[], String s3[], String s4[], String s5[]) {
        context = ct;
        data1 = s1;
        data2 = s2;
        data3 = s3;
        data4 = s4;
        data5 = s5;
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
        holder.myText1.setText("Name: " + data1[position]);
        holder.myText2.setText("Date of birth: " + data2[position]);
        holder.myText3.setText("Sex: " + data3[position]);
        holder.myText4.setText("Phone: " + data4[position]);
        holder.myText5.setText("Email: " + data5[position]);

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2, myText3, myText4, myText5;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.txtNameTeacher);
            myText2 = itemView.findViewById(R.id.txtDateOfBirthTeacher);
            myText3 = itemView.findViewById(R.id.txtSexTeacher);
            myText4 = itemView.findViewById(R.id.txtPhoneTeacher);
            myText5 = itemView.findViewById(R.id.txtMailTeacher);

        }
    }

}
