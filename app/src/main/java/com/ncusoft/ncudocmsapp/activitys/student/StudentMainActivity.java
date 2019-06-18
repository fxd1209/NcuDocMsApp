package com.ncusoft.ncudocmsapp.activitys.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.activitys.teacher.AdminAddCourseActivity;
import com.ncusoft.ncudocmsapp.activitys.teacher.TeacherCourseActivity;
import com.ncusoft.ncudocmsapp.activitys.teacher.TeacherInfoActivity;
import com.ncusoft.ncudocmsapp.activitys.teacher.TeacherSelectCouActivity;
import com.ncusoft.ncudocmsapp.pojo.Student;

public class StudentMainActivity extends BaseActivity {

    private Button btnCourseList;
    private Button btnSelectCourse;
    private Button btnAddCourse;
    private Button btnPersonInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        initBaseToolbar("主菜单");
        InitBtns(); //初始化按钮，并绑定监听事件
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public void InitBtns(){
        btnCourseList=findViewById(R.id.btn_student_courseList);
        btnSelectCourse=findViewById(R.id.btn_student_selectCourse);
        btnAddCourse=findViewById(R.id.btn_student_addCourse);
//        btnPersonInfo=findViewById(R.id.btn_stu_info);
        btnCourseList.setOnClickListener(new StudentBtnListener());
        btnSelectCourse.setOnClickListener(new StudentBtnListener());
        btnAddCourse.setOnClickListener(new StudentBtnListener());
//        btnPersonInfo.setOnClickListener(new TeacherBtnListener());
    }
        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_student_myclass:
                Toast.makeText(this, "我的课程", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_student_selectclass:
                Toast.makeText(this, "选择课程", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_student_addclass:
               Toast.makeText(this, "添加课程", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_student_mymessage:
                Toast.makeText(this, "个人信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_student_notice:
                Toast.makeText(this, "信息通知", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_student_appraise:
                Toast.makeText(this, "学生评价", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    class StudentBtnListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_student_courseList:
                    startActivity(new Intent().setClass(StudentMainActivity.this, StudentCourseActivity.class));
                    break;
                case R.id.btn_student_selectCourse:
                    startActivity(new Intent().setClass(StudentMainActivity.this, StudentSelectCouActivity.class));
                    break;
                case R.id.btn_student_addCourse:
                    startActivity(new Intent().setClass(StudentMainActivity.this, AdminAddCourseActivity.class));
                    break;
                case R.id.btn_student_info:
                    startActivity(new Intent().setClass(StudentMainActivity.this, TeacherInfoActivity.class));
                    break;
                    default:
            }

        }
    }
}



