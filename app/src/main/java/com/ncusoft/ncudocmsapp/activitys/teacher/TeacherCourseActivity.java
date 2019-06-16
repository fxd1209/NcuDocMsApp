package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.os.Bundle;
import android.widget.GridView;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.activitys.course.CourseGridAdapter;
import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;

import java.util.ArrayList;
import java.util.List;

public class TeacherCourseActivity extends BaseActivity {

    GridView courseGirdView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_course);
        courseGirdView=(GridView)findViewById(R.id.teacher_course_grid_view);
        TeacherCourse c1=new TeacherCourse();
        c1.setCourse(new Course.CourseBuilder()
        .name("高数").build());
        c1.setClassCount("选修一班");
        c1.setTerm("第二学期");
        TeacherCourse c2=new TeacherCourse();
        c2.setCourse(new Course.CourseBuilder()
                .name("高数").build());
        c2.setClassCount("选修一班");
        c2.setTerm("第二学期");
        TeacherCourse c3=new TeacherCourse();
        c3.setCourse(new Course.CourseBuilder()
                .name("高数").build());
        c3.setClassCount("选修一班");
        c3.setTerm("第二学期");
        List<TeacherCourse> teacherCourseList=new ArrayList<>();
        teacherCourseList.add(c1);
        teacherCourseList.add(c2);
        teacherCourseList.add(c3);
        courseGirdView.setAdapter(new CourseGridAdapter(TeacherCourseActivity.this,teacherCourseList));
    }

    //toolbar菜单栏
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.menu_1:
//                Toast.makeText(this, "我是第一个", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.menu_2:
//                Toast.makeText(this, "我是第二个", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.menu_3:
//                Toast.makeText(this, "我是第三个", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.menu_4:
//                Toast.makeText(this, "我是第四个", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return true;
//    }

}
