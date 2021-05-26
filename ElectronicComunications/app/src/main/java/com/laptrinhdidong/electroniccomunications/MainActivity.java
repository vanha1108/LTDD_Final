package com.laptrinhdidong.electroniccomunications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import com.laptrinhdidong.electroniccomunications.fragment.ClassFragment;
import com.laptrinhdidong.electroniccomunications.fragment.FacultyFragment;
import com.laptrinhdidong.electroniccomunications.fragment.GradeFragment;
import com.laptrinhdidong.electroniccomunications.fragment.HomeFragment;
import com.laptrinhdidong.electroniccomunications.fragment.LogOutFragment;
import com.laptrinhdidong.electroniccomunications.fragment.ParentFragment;
import com.laptrinhdidong.electroniccomunications.fragment.ScheduleFragment;
import com.laptrinhdidong.electroniccomunications.fragment.StudentFragment;
import com.laptrinhdidong.electroniccomunications.fragment.SubjectFragment;
import com.laptrinhdidong.electroniccomunications.fragment.TeacherFragment;
import com.laptrinhdidong.electroniccomunications.fragment.TimetableFragment;
import com.laptrinhdidong.electroniccomunications.fragment.TuitionFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_admin);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view_admin_default);
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
                        new FacultyFragment()).commit();
                break;
            case R.id.nav_class:
                toolbar.setTitle("Class Information");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ClassFragment()).commit();
                break;
            case R.id.nav_teacherinfor:
                toolbar.setTitle("Teacher Information");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TeacherFragment()).commit();
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
                        new TuitionFragment()).commit();
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
                        new TimetableFragment()).commit();
                break;
            case R.id.nav_subject:
                toolbar.setTitle("Subject");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SubjectFragment()).commit();
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