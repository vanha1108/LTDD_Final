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
    private HolderLongClick holderLongClick;

    public SubjectAdapter(List<SubjectEntity> subjectEntities, Context context) {
        this.context = context;
        this.subjects = subjectEntities;
    }

    public SubjectAdapter(List<SubjectEntity> subjectEntities, Context context, HolderLongClick holderLongClick) {
        this.context = context;
        this.subjects = subjectEntities;
        this.holderLongClick = holderLongClick;
    }

    public SubjectAdapter(HolderLongClick holderLongClick) {
        this.holderLongClick = holderLongClick;
    }

    public void setData(List<SubjectEntity> mListSubject) {
        subjects = mListSubject;
        notifyDataSetChanged();
    }

    public interface HolderLongClick {
        void showDialog(SubjectEntity subjectEntity);
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
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holderLongClick.showDialog(subject);
                return true;
            }
        });
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
        View mView;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtSubjectNameAdmin);
            txtCredit = itemView.findViewById(R.id.txtSubjectCreditAdmin);
            txtMoney = itemView.findViewById(R.id.txtSubjectMoneyAdmin);
            mView = itemView;
        }
    }
}
