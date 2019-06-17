package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.os.Bundle;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;

public class TeacherInfoActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.teacher_info);
        initBaseToolbar("个人信息");
    }
}
