package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.StudentCourse;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;
import com.ncusoft.ncudocmsapp.service.user.TeacherService;
import com.ncusoft.ncudocmsapp.service.user.TeacherServiceInterface;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class TeacherStuListActivity extends BaseActivity {

    private ListView stuListView;
    TeacherServiceInterface teacherService;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_stulist);
        initBaseToolbar("学生列表");
        teacherService=new TeacherService();
        stuListView=findViewById(R.id.teacher_tc_stuList);
        //获取上个界面传来的TeacherCourse信息
        Intent intent=getIntent();
        TeacherCourse teacherCourse=(TeacherCourse)intent.getSerializableExtra("teacherCourse");
        //根据TeacherCourse来查询学生列表
        Map<Course,ArrayList<StudentCourse>> map=teacherService.getStudentList(teacherCourse);
        ArrayList<StudentCourse> scList=new ArrayList<>();
        Set<Course> courseSet=map.keySet();
        for (Course course:courseSet){
            scList=map.get(course);
        }
        if(scList!=null){
            stuListView.setAdapter(new TeacherStuListAdapter(TeacherStuListActivity.this,scList));
        }
    }
}
