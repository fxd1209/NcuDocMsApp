package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.course.CourseGridAdapter;
import com.ncusoft.ncudocmsapp.pojo.Course;

import java.util.ArrayList;
import java.util.List;

public class TeacherMainActivity extends AppCompatActivity {

    GridView courseGirdView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);
        courseGirdView=(GridView)findViewById(R.id.teacher_course_grid_view);
        Course c1=new Course();
        c1.setName("高数");
        c1.setTerm("第一学期");
        c1.setClassCount("选修一班");
        Course c2=new Course();
        c2.setName("高数啊");
        c2.setTerm("第二学期");
        c2.setClassCount("选修二班");
        Course c3=new Course();
        c3.setName("高数啊啊");
        c3.setTerm("第三学期");
        c3.setClassCount("选修三班");
        List<Course> courseList=new ArrayList<>();
        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        courseGirdView.setAdapter(new CourseGridAdapter(TeacherMainActivity.this,courseList));
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
