package com.laptrinhdidong.electroniccomunications.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.model.TestScheduleEntity;
import com.laptrinhdidong.electroniccomunications.utils.TimeLesson;

import java.util.List;

public abstract class TestScheduleAdapter extends RecyclerView.Adapter<TestScheduleAdapter.TestScheduleViewHolder> {

    private List<TestScheduleEntity> testScheduleEntities;
    Context context;
    private HolderLongClick holderLongClick;

    public TestScheduleAdapter(List<TestScheduleEntity> testScheduleEntities, Context context, HolderLongClick holderLongClick) {
        this.testScheduleEntities = testScheduleEntities;
        this.context = context;
        this.holderLongClick = holderLongClick;
    }

    public TestScheduleAdapter(List<TestScheduleEntity> testScheduleEntities, Context context) {
        this.testScheduleEntities = testScheduleEntities;
        this.context = context;
    }

    public void setData(List<TestScheduleEntity> timeTables) {
        this.testScheduleEntities = testScheduleEntities;
        notifyDataSetChanged();
    }

    public void clear() {
    }

    public abstract void showDialog(TestScheduleEntity testScheduleEntity);

    public interface HolderLongClick {
        void showDialog(TestScheduleEntity testScheduleEntity);
    }

    @NonNull
    @Override
    public TestScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_schedule, parent, false);
        return new TestScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestScheduleViewHolder holder, int position) {
        TestScheduleEntity testSchedule = testScheduleEntities.get(position);
        if (testSchedule == null) {
            return;
        }
        holder.txtSubject.setText("Subject: " + testSchedule.getSubjectName());
        holder.txtTeacher.setText("Teacher: " + testSchedule.getTeacherName());
        // Handler time
        String startTime = TimeLesson.getInstance().getValue(testSchedule.getStartLesson());
        int finish = Integer.parseInt(testSchedule.getStartLesson()) + Integer.parseInt(testSchedule.getNumberLesson());
        String finishTime = TimeLesson.getInstance().getValue(String.valueOf(finish));
        holder.txtTime.setText("Time: " + startTime + " - " + finishTime);
        holder.txtRoom.setText("Room: " + testSchedule.getRoomName());
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holderLongClick.showDialog(testSchedule);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (testScheduleEntities != null) {
            return testScheduleEntities.size();
        }
        return 0;
    }

    public class TestScheduleViewHolder extends RecyclerView.ViewHolder {

        TextView txtSubject, txtTeacher, txtTime, txtRoom;
        View mView;

        public TestScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSubject = itemView.findViewById(R.id.txtSubjectItemTestSchedule);
            txtTeacher = itemView.findViewById(R.id.txtTeacherItemTestSchedule);
            txtTime = itemView.findViewById(R.id.txtTimeItemTestSchedule);
            txtRoom = itemView.findViewById(R.id.txtRoomItemTestSchedule);
            mView = itemView;
        }
    }
}
