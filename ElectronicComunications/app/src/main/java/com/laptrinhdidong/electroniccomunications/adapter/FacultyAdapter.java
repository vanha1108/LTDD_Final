package com.laptrinhdidong.electroniccomunications.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.model.FacultyEntity;

import java.util.List;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder> {

    private List<FacultyEntity> facultys;
    Context context;
    private HolderLongClick holderLongClick;

    public FacultyAdapter(List<FacultyEntity> facultyEntities, Context context) {
        this.context = context;
        this.facultys = facultyEntities;
    }

    public FacultyAdapter(List<FacultyEntity> facultyEntities, Context context, HolderLongClick holderLongClick) {
        this.context = context;
        this.facultys = facultyEntities;
        this.holderLongClick = holderLongClick;
    }

    public FacultyAdapter(HolderLongClick holderLongClick) {
        this.holderLongClick = holderLongClick;
    }

    public void setData(List<FacultyEntity> mListFaculty) {
        facultys = mListFaculty;
        notifyDataSetChanged();
    }


    public interface HolderLongClick {
        void showDialog(FacultyEntity facultyEntity);
    }

    @NonNull
    @Override
    public FacultyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_faculty, parent, false);
        return new FacultyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewHolder holder, int position) {
        FacultyEntity faculty = facultys.get(position);
        if (faculty == null) {
            return;
        }
        holder.nameFaculty.setText("Faculty Name: " + faculty.getFacultyName());
        holder.nameDean.setText("Dean Name: " + faculty.getDeanName());
        holder.addressDean.setText("Contact Address of Dean: " + faculty.getAddressDean());
        holder.nameAssistant.setText("Assistant Name: " + faculty.getAssistantName());
        holder.addressAssistant.setText("Contact Address of Assistant: "
                + faculty.getAddressAssistant());
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holderLongClick.showDialog(faculty);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (facultys != null) {
            return facultys.size();
        }
        return 0;
    }

    public class FacultyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameFaculty, nameDean, addressDean, nameAssistant, addressAssistant;
        View mView;

        public FacultyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameFaculty = (TextView) itemView.findViewById(R.id.txtNameFaculty);
            nameDean = (TextView) itemView.findViewById(R.id.txtNameDean);
            addressDean = (TextView) itemView.findViewById(R.id.txtAddressDean);
            nameAssistant = (TextView) itemView.findViewById(R.id.txtNameAssistant);
            addressAssistant = (TextView) itemView.findViewById(R.id.txtAddressAssistant);

            mView = itemView;
        }
    }
}