package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.os.Bundle;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;

public class StudentCourseActivity extends BaseActivity {
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course);
        initBaseToolbar("我的课程");
    }
}
