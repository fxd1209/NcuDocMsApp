package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.activitys.login.LoginActivity;

public class TeacherMainActivity extends BaseActivity {

    private Button btnCourseList;
    private Button btnSelectCourse;
    private Button btnAddCourse;
    private Button btnPersonInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);
        InitBtns(); //初始化按钮，并绑定监听事件

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_base,menu);
        return true;
    }

    public void InitBtns(){
        btnCourseList=findViewById(R.id.btn_teacher_courseList);
        btnSelectCourse=findViewById(R.id.btn_teacher_selectCourse);
        btnAddCourse=findViewById(R.id.btn_teacher_addCourse);
        btnPersonInfo=findViewById(R.id.btn_teacher_info);
        btnCourseList.setOnClickListener(new TeacherBtnListener());
        btnSelectCourse.setOnClickListener(new TeacherBtnListener());
        btnAddCourse.setOnClickListener(new TeacherBtnListener());
        btnPersonInfo.setOnClickListener(new TeacherBtnListener());
    }
        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_teacher_myclass:
                Toast.makeText(this, "我的课程", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_teacher_selectclass:
                Toast.makeText(this, "选择课程", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_teacher_addclass:
               Toast.makeText(this, "添加课程", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_teacher_mymessage:
                Toast.makeText(this, "个人信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_teacher_notice:
                Toast.makeText(this, "信息通知", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_teacher_appraise:
                Toast.makeText(this, "学生评价", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    class TeacherBtnListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_teacher_courseList:
                    startActivity(new Intent().setClass(TeacherMainActivity.this, TeacherCourseActivity.class));
                    break;
                case R.id.btn_teacher_selectCourse:
                    break;
                case R.id.btn_teacher_addCourse:
                    break;
                case R.id.btn_teacher_info:
                    break;
                    default:
            }

        }
    }
}



