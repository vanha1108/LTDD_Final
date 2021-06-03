package com.laptrinhdidong.electroniccomunications;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.laptrinhdidong.electroniccomunications.adapter.NotificationAdapter;
import com.laptrinhdidong.electroniccomunications.fragment.ClassesFragmentAdmin;
import com.laptrinhdidong.electroniccomunications.fragment.FacultyAdminFragment;
import com.laptrinhdidong.electroniccomunications.fragment.GradeFragment;
import com.laptrinhdidong.electroniccomunications.fragment.HomeFragment;
import com.laptrinhdidong.electroniccomunications.fragment.LogOutFragment;
import com.laptrinhdidong.electroniccomunications.fragment.ParentFragment;
import com.laptrinhdidong.electroniccomunications.fragment.ScheduleFragment;
import com.laptrinhdidong.electroniccomunications.fragment.StudentFragment;
import com.laptrinhdidong.electroniccomunications.fragment.SubjectAdminFragment;
import com.laptrinhdidong.electroniccomunications.fragment.TeacherFragmentAdmin;
import com.laptrinhdidong.electroniccomunications.fragment.TimeTableFragment;
import com.laptrinhdidong.electroniccomunications.fragment.TuitionFragmentAdmin;
import com.laptrinhdidong.electroniccomunications.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class HomeAdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private RecyclerView recyclerHomeAdmin;
    private List<Notification> notifications = new ArrayList<>();
    private NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        toolbar = findViewById(R.id.toolbar_home_admin);
        drawerLayout = findViewById(R.id.drawer_layout_home_admin);
        navigationView = findViewById(R.id.nav_view_home_admin);
        recyclerHomeAdmin = findViewById(R.id.recyclerHomeAdmin);

        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_menu, R.string.close_menu);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                toolbar.setTitle("Home");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_facultyinfor:
                toolbar.setTitle("Faculty Information");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FacultyAdminFragment()).commit();
                break;
            case R.id.nav_class:
                toolbar.setTitle("Class Information");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ClassesFragmentAdmin()).commit();
                break;
            case R.id.nav_teacherinfor:
                toolbar.setTitle("Teacher Information");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TeacherFragmentAdmin()).commit();
                break;
            case R.id.nav_parentinfor:
                toolbar.setTitle("Parent Information");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ParentFragment()).commit();
                break;
            case R.id.nav_studentinfor:
                toolbar.setTitle("Student Information");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StudentFragment()).commit();
                break;
            case R.id.nav_tuition:
                toolbar.setTitle("Tuition Information");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TuitionFragmentAdmin()).commit();
                break;
            case R.id.nav_grade:
                toolbar.setTitle("Grade");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new GradeFragment()).commit();
                break;
            case R.id.nav_testschedule:
                toolbar.setTitle("Test Schedule");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ScheduleFragment()).commit();
                break;
            case R.id.nav_timetable:
                toolbar.setTitle("Time Table");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TimeTableFragment()).commit();
                break;
            case R.id.nav_subject:
                toolbar.setTitle("Subject");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SubjectAdminFragment()).commit();
                break;
            case R.id.nav_logout:
                toolbar.setTitle("Log Out");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LogOutFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}