package com.ncusoft.ncudocmsapp.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.course.CourseGridAdapter;
import com.ncusoft.ncudocmsapp.activitys.teacher.TeacherCourseActivity;
import com.ncusoft.ncudocmsapp.pojo.Course;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ClientApplication.addActivity(this);
    }



}
