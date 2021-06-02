package com.laptrinhdidong.electroniccomunications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.laptrinhdidong.electroniccomunications.adapter.NotificationAdapter;
import com.laptrinhdidong.electroniccomunications.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class HomeStudentActivity extends AppCompatActivity {

    private RecyclerView recyclerHomeStudent;
    private List<Notification> notifications = new ArrayList<>();
    private NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_student);

        recyclerHomeStudent = findViewById(R.id.recyclerHomeStudent);

        Notification notification = new Notification("Notification 1", "This is content of notification 1");
        notifications.add(notification);
        Notification notification2 = new Notification("Notification 2", "This is content of notification 2");
        notifications.add(notification2);
        Notification notification3 = new Notification("Notification 3", "This is content of notification 3");
        notifications.add(notification3);

        notificationAdapter = new NotificationAdapter(notifications, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerHomeStudent.setLayoutManager(linearLayoutManager);
        recyclerHomeStudent.setAdapter(notificationAdapter);
    }
}