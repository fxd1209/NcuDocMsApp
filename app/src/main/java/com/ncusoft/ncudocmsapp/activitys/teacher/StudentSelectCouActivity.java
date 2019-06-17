package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.os.Bundle;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;

public class StudentSelectCouActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_selectcourse);
        initBaseToolbar("选择课程");
    }
}
