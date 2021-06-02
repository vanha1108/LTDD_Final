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
import com.laptrinhdidong.electroniccomunications.model.TimeTableEntity;
import com.laptrinhdidong.electroniccomunications.utils.TimeLesson;

import java.util.List;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.TimeTableViewHolder> {

    private List<TimeTableEntity> timeTables;
    Context context;
    private HolderLongClick holderLongClick;

    public TimeTableAdapter(List<TimeTableEntity> timeTables, Context context, HolderLongClick holderLongClick) {
        this.timeTables = timeTables;
        this.context = context;
        this.holderLongClick = holderLongClick;
    }

    public TimeTableAdapter(List<TimeTableEntity> timeTables, Context context) {
        this.timeTables = timeTables;
        this.context = context;
    }

    public void setData(List<TimeTableEntity> timeTables) {
        this.timeTables = timeTables;
        notifyDataSetChanged();
    }

    public interface HolderLongClick {
        void showDialog(TimeTableEntity timeTableEntity);
    }

    @NonNull
    @Override
    public TimeTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_table, parent, false);
        return new TimeTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableViewHolder holder, int position) {
        TimeTableEntity timeTable = timeTables.get(position);
        if (timeTable == null) {
            return;
        }
        holder.txtSubject.setText("Subject: " + timeTable.getSubjectName());
        holder.txtTeacher.setText("Teacher: " + timeTable.getTeacherName());
        // Handler time
        String startTime = TimeLesson.getInstance().getValue(timeTable.getStartLesson());
        int finish = Integer.parseInt(timeTable.getStartLesson()) + Integer.parseInt(timeTable.getNumberLesson());
        String finishTime = TimeLesson.getInstance().getValue(String.valueOf(finish));
        holder.txtTime.setText("Time: " + startTime + " - " + finishTime);
        holder.txtRoom.setText("Room: " + timeTable.getRoomName());
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holderLongClick.showDialog(timeTable);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (timeTables != null) {
            return timeTables.size();
        }
        return 0;
    }

    public class TimeTableViewHolder extends RecyclerView.ViewHolder {

        TextView txtSubject, txtTeacher, txtTime, txtRoom;
        View mView;

        public TimeTableViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSubject = itemView.findViewById(R.id.txtSubjectItemTimeTable);
            txtTeacher = itemView.findViewById(R.id.txtTeacherItemTimeTable);
            txtTime = itemView.findViewById(R.id.txtTimeItemTimeTable);
            txtRoom = itemView.findViewById(R.id.txtRoomItemTimeTable);
            mView = itemView;
        }
    }
}
