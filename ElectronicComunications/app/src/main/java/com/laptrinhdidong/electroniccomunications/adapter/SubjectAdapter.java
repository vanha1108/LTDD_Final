package com.laptrinhdidong.electroniccomunications.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.model.SubjectEntity;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private List<SubjectEntity> subjects;
    Context context;

    public SubjectAdapter(List<SubjectEntity> subjectEntities, Context context) {
        this.context = context;
        this.subjects = subjectEntities;
    }

    public void setData(List<SubjectEntity> mListSubject) {
        subjects = mListSubject;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        SubjectEntity subject = subjects.get(position);
        if (subject == null) {
            return;
        }
        holder.txtName.setText("Name: " + subject.getName());
        holder.txtCredit.setText("Credit: " + subject.getCredit());
        holder.txtMoney.setText("Money/Credit: " + subject.getMoneyPerCredit());
    }

    @Override
    public int getItemCount() {
        if (subjects != null) {
            return subjects.size();
        }
        return 0;
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtCredit, txtMoney;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtSubjectNameAdmin);
            txtCredit = itemView.findViewById(R.id.txtSubjectCreditAdmin);
            txtMoney = itemView.findViewById(R.id.txtSubjectMoneyAdmin);
        }
    }
}
