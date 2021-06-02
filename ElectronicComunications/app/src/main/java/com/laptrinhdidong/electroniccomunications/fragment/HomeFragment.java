package com.laptrinhdidong.electroniccomunications.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laptrinhdidong.electroniccomunications.R;
import com.laptrinhdidong.electroniccomunications.adapter.NotificationAdapter;
import com.laptrinhdidong.electroniccomunications.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerHome;
    private List<Notification> notifications = new ArrayList<>();
    private NotificationAdapter notificationAdapter;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle saveInsatnceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
//        Notification notification = new Notification("Notification 1", "This is content of notification 1");
//        notifications.add(notification);
//        Notification notification2 = new Notification("Notification 2", "This is content of notification 2");
//        notifications.add(notification2);
//        Notification notification3 = new Notification("Notification 3", "This is content of notification 3");
//        notifications.add(notification3);
//
//        recyclerHome = view.findViewById(R.id.recyclerHome);
//        notificationAdapter = new NotificationAdapter(notifications, getContext());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
//        recyclerHome.setLayoutManager(linearLayoutManager);
//        recyclerHome.setAdapter(notificationAdapter);
    }
}
