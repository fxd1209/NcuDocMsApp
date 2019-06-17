package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.os.Bundle;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;

public class TeacherEvaluate extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_evaluate);
        initBaseToolbar("学生评价");
    }
}
