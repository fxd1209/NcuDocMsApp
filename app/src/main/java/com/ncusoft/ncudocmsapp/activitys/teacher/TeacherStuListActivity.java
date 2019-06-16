package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.pojo.StudentCourse;

import java.util.ArrayList;

public class TeacherStuListActivity extends BaseActivity {

    private ListView stuListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_stulist);
        stuListView=findViewById(R.id.teacher_tc_stuList);
        Intent intent=getIntent();
        ArrayList<StudentCourse> stuList=(ArrayList<StudentCourse>) intent.getSerializableExtra("stuList");
        if(stuList!=null){
            stuListView.setAdapter(new TeacherStuListAdapter(TeacherStuListActivity.this,stuList));
        }
    }
}
