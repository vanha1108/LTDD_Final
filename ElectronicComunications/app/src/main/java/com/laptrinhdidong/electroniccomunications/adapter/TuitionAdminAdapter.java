package com.laptrinhdidong.electroniccomunications.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.model.Tuition;

import java.util.ArrayList;
import java.util.List;


public class TuitionAdminAdapter extends RecyclerView.Adapter<TuitionAdminAdapter.MyViewHolder> {


    ArrayList<Tuition> tuitions;
    Context context;
    private OnLongClick onLongClick;

    public TuitionAdminAdapter(Context context, ArrayList<Tuition> tuitions, OnLongClick onLongClick) {
        this.tuitions = tuitions;
        this.context = context;
        this.onLongClick = onLongClick;
    }

    public interface OnLongClick {
        void showDialogUpdateDelete(Tuition tuition
        );
    }

    public void setData (ArrayList<Tuition> mListTuitions) {
        tuitions = mListTuitions;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tuition_admin_itemview, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Tuition tuition = tuitions.get(position);

        holder.myText1.setText(tuition.getSubject());
        holder.myText2.setText(Integer.toString(tuition.getPrice()));
        holder.myText3.setText(Integer.toString(tuition.getNoC()));
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onLongClick.showDialogUpdateDelete(tuition);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        if (tuitions != null) {
            return tuitions.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2, myText3;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.txtTuitionSubjectAdmin);
            myText2 = itemView.findViewById(R.id.txtTuitionPriceAdmin);
            myText3 = itemView.findViewById(R.id.txtTuitionNoCAdmin);

        }
    }

}
