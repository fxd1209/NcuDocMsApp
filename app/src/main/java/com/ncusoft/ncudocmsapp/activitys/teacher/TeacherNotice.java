package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.os.Bundle;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;

public class TeacherNotice extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_notice);
        initBaseToolbar("信息通知");
    }
}
