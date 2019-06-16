package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.app.ActionBar;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.Toolbar;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.activitys.course.CourseGridAdapter;
import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.Teacher;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;
import com.ncusoft.ncudocmsapp.repository.course.CourseDao;
import com.ncusoft.ncudocmsapp.repository.course.TeacherCourseDao;
import com.ncusoft.ncudocmsapp.service.user.TeacherService;
import com.ncusoft.ncudocmsapp.service.user.TeacherServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TeacherCourseActivity extends BaseActivity {

    public static final String TAG="teacher.TCActivity";

    GridView courseGirdView;
    View toolbarView;
    Toolbar baseBar;
    TeacherServiceInterface teacherService=new TeacherService();

    //TODO 测试用
    CourseDao courseDao=CourseDao.getInstance();
    TeacherCourseDao teacherCourseDao=TeacherCourseDao.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_course);
        toolbarView= findViewById(R.id.teacher_main_toolbal);
        baseBar=(Toolbar)toolbarView.findViewById(R.id.toolbar_base);
        setActionBar(baseBar);
        courseGirdView=(GridView)findViewById(R.id.teacher_course_grid_view);

//        //测试用
//        ContentValues cvCourse1=new Course.CourseBuilder()
//                .id("c123")
//                .name("高等数学")
//                .credit("3")
//                .build().toContentValues();
//        ContentValues cvCourse2=new Course.CourseBuilder()
//                .id("c456")
//                .name("大学语文")
//                .credit("2")
//                .build().toContentValues();
//        courseDao.insert(cvCourse1);
//        courseDao.insert(cvCourse2);
//
//        ContentValues cvTc1=new TeacherCourse.TeacherCourseBuilder()
//                .id(null)
//                .teacherId("18748980084")
//                .courseId("c123")
//                .term("2018-2019-2")
//                .classCount("2")
//                .build().toContentValues();
//        ContentValues cvTc2=new TeacherCourse.TeacherCourseBuilder()
//                .id(null)
//                .teacherId("18748980084")
//                .courseId("c456")
//                .term("2018-2019-1")
//                .classCount("1")
//                .build().toContentValues();
//        ContentValues cvTc3=new TeacherCourse.TeacherCourseBuilder()
//                .id(null)
//                .teacherId("18798891209")
//                .courseId("c123")
//                .term("2018-2020-1")
//                .classCount("5")
//                .build().toContentValues();
//
//        teacherCourseDao.insert(cvTc1);
//        teacherCourseDao.insert(cvTc2);
//        teacherCourseDao.insert(cvTc3);

        Map<Teacher,List<TeacherCourse>> tcourseListMap=teacherService.getTeacherCourseByTeacherId("18748980084");
        Set<Teacher> teacherSet=tcourseListMap.keySet();
        List<TeacherCourse> list=new ArrayList<>();
        for (Teacher teacher :teacherSet){
            list=tcourseListMap.get(teacher);
            Log.i(TAG+"教师",teacher.toString());
            for (TeacherCourse tc:list){
                Log.i(TAG+"课程",tc.toString());
            }
        }
        courseGirdView.setAdapter(new CourseGridAdapter(TeacherCourseActivity.this,list));
    }

////    //toolbar菜单栏
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_toolbar_base,menu);
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
