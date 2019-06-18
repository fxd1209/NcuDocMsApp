package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.activitys.course.TeacherCourseAdapter;
import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.StudentCourse;
import com.ncusoft.ncudocmsapp.pojo.Teacher;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;
import com.ncusoft.ncudocmsapp.repository.course.CourseDao;
import com.ncusoft.ncudocmsapp.repository.course.StudentCourseDao;
import com.ncusoft.ncudocmsapp.repository.course.TeacherCourseDao;
import com.ncusoft.ncudocmsapp.service.course.CourseService;
import com.ncusoft.ncudocmsapp.service.course.CourseServiceInterface;
import com.ncusoft.ncudocmsapp.service.user.TeacherService;
import com.ncusoft.ncudocmsapp.service.user.TeacherServiceInterface;
import com.ncusoft.ncudocmsapp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TeacherCourseActivity extends BaseActivity {

    public static final String TAG="teacher.TCActivity";
    private int currentSel; //当前点击的课程
    GridView courseGirdView;
    TeacherCourseAdapter tcAdapter;
    List<TeacherCourse> list;
    Map<Teacher,List<TeacherCourse>> tCourseListMap;//当前教师选课信息

    TeacherServiceInterface teacherService=new TeacherService();
    CourseServiceInterface courseService=new CourseService();

    //TODO 测试用
    CourseDao courseDao=CourseDao.getInstance();
    TeacherCourseDao teacherCourseDao=TeacherCourseDao.getInstance();
    StudentCourseDao studentCourseDao=StudentCourseDao.getInstance();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_course);
        initBaseToolbar("我的课程");

//        //测试用
//        ContentValues cvCourse1=new Course.CourseBuilder()
//                .id("c789")
//                .name("高等数学")
//                .credit("3")
//                .build().toContentValues();
//        ContentValues cvCourse2=new Course.CourseBuilder()
//                .id("c129")
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
//        ContentValues cvSc1=new StudentCourse.StudentCourseBuilder()
//            .id(null)
//            .studentId("18780451091")
//            .courseId("c123")
//            .term("2018-2019-2")
//            .classCount("2")
//            .build().toContentValues();
//        ContentValues cvSc2=new StudentCourse.StudentCourseBuilder()
//            .id(null)
//            .studentId("18780451091")
//            .courseId("c456")
//            .term("2018-2019-1")
//            .classCount("1")
//            .build().toContentValues();
//        ContentValues cvSc4=new StudentCourse.StudentCourseBuilder()
//                .id(null)
//                .studentId("18798891209")
//                .courseId("c456")
//                .term("2018-2019-1")
//                .classCount("1")
//                .build().toContentValues();
//        ContentValues cvSc3=new StudentCourse.StudentCourseBuilder()
//            .id(null)
//            .studentId("18798891209")
//            .courseId("c123")
//            .term("2018-2020-1")
//            .classCount("5")
//            .build().toContentValues();
//        studentCourseDao.insert(cvSc1);
//        studentCourseDao.insert(cvSc2);
//        studentCourseDao.insert(cvSc3);
//        studentCourseDao.insert(cvSc4);

        courseGirdView = (GridView) findViewById(R.id.teacher_course_grid_view);

        //查询当前登录者选课信息

        tCourseListMap=teacherService.getTeaCourseByTeacherId(
                ClientApplication.getInstance().getCurrentLoginUser().getId());
        Set<Teacher> teacherSet=tCourseListMap.keySet();
        for (Teacher teacher : teacherSet){
            if (teacher==null) break;
            list=tCourseListMap.get(teacher);
        }
        tcAdapter=new TeacherCourseAdapter(TeacherCourseActivity.this,list);
        courseGirdView.setAdapter(tcAdapter);
        registerForContextMenu(courseGirdView);
        courseGirdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentSel=position; //记录点击的课程位置
                courseGirdView.showContextMenu();
            }
        });
    } //end onCreate

    @Override
    public void onStart(){
        super.onStart();
        //重新从数据库读出数据
        Map<Teacher,List<TeacherCourse>> tempMap=null;
        List<TeacherCourse> tempList=null;
        tempMap=teacherService.getTeaCourseByTeacherId(
                ClientApplication.getInstance().getCurrentLoginUser().getId());
        Set<Teacher> teacherSet=tempMap.keySet();
        for (Teacher teacher : teacherSet){
            if (teacher==null) break;
            tempList=tempMap.get(teacher);
        }

        //如果读出的数据和原来的list大小不一致，说明数据改变了
        if (!(tempList!=null && tempList.size()==list.size())){
            list=tempList;
            tcAdapter.setCourseList(list);
            tcAdapter.refreshDataSet();
        }

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.add(0,0,0,"查看学生");
        menu.add(0,1,1,"添加学生");
        menu.add(0,2,2,"编辑课程");
        menu.add(0,3,3,"删除课程");
        super.onCreateContextMenu(menu,v,menuInfo);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 0: //点击查看学生
                Intent intent=new Intent();
                Bundle bundle =new Bundle();
                intent.setClass(TeacherCourseActivity.this,TeacherStuListActivity.class);
                bundle.putSerializable("teacherCourse",list.get(currentSel));
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 1: //点击添加学生
                break;
            case 2: //点击编辑课程
                break;
            case 3: //点击删除课程
                //可以设置三个按钮
                AlertDialog dlg
                        =new AlertDialog.Builder(TeacherCourseActivity.this)
                        .setIcon(R.drawable.icon_doubt)
                        .setTitle("警告")
                        .setMessage("确定删除吗？")
                        .setPositiveButton("确定删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TeacherCourse tcList=list.get(currentSel);
                                if (0==courseService.deleteTeacherCourse(tcList)){
                                    ToastUtil.initToast(TeacherCourseActivity.this,
                                            ToastUtil.ToastType.FAIL,"删除失败!",
                                            Toast.LENGTH_LONG,new Point(0,0)).show();
                                }else{
                                    ToastUtil.initToast(TeacherCourseActivity.this,
                                            ToastUtil.ToastType.SUCCESS,"删除成功!",
                                            Toast.LENGTH_LONG,new Point(0,0)).show();
                                    //移除数据
                                    list.remove(currentSel);
                                    tcAdapter.remove(currentSel);
                                    tcAdapter.refreshDataSet();

                                }
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("c");
                            }
                        }).setNeutralButton("算了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("a");
                            }
                        }).show();

                break;
            default:break;
        }
        return  super.onContextItemSelected(item);
    }

}


/***
 * 设置action bar ，需要主题样式里面有actionbar
 */
//  actionBar=getActionBar();
//          actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//          actionBar.setCustomView(R.layout.toolbal_base);//自定义ActionBar布局
//          actionBar.getCustomView().setOnClickListener(new View.OnClickListener() {//监听事件
//@Override
//public void onClick(View arg0) {
//        switch (arg0.getId()) {
////                    case R.id.back:
////                        showToast(R.string.finish);
////                        finish();
////                        break;
//default:
//        break;
//        }
//        }
//        });
/*******************
 *end
 *****************/





//    //toolbar菜单栏
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


