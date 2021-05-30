package com.laptrinhdidong.electroniccomunications.adapter;

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

public class FacultyAdapter extends FirebaseRecyclerAdapter<FacultyEntity, FacultyAdapter.myviewholder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FacultyAdapter(@NonNull FirebaseRecyclerOptions<FacultyEntity> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull FacultyEntity model) {

        holder.nameFaculty.setText("Faculty Name: " + model.getFacultyName());
        holder.nameDean.setText("Dean Name: " + model.getDeanName());
        holder.addressDean.setText("Contact Address of Dean: " + model.getAddressDean());
        holder.nameAssistant.setText("Assistant Name: " + model.getAssistantName());
        holder.addressAssistant.setText("Contact Address of Assistant: " + model.getAddressAssistant());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faculty,
                parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView nameFaculty, nameDean, addressDean, nameAssistant, addressAssistant;
        public myviewholder(@NonNull View itemView) {

            super(itemView);
            nameFaculty = (TextView)itemView.findViewById(R.id.txtNameFaculty);
            nameDean = (TextView)itemView.findViewById(R.id.txtNameDean);
            addressDean = (TextView)itemView.findViewById(R.id.txtAddressDean);
            nameAssistant = (TextView)itemView.findViewById(R.id.txtNameAssistant);
            addressAssistant = (TextView)itemView.findViewById(R.id.txtAddressAssistant);

        }
    }
}