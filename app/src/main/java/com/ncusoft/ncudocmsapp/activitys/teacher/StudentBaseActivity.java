package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.teacher.BaseToolBarMenuSelectedListener;
import com.ncusoft.ncudocmsapp.activitys.teacher.TeacherCourseActivity;

public class StudentBaseActivity extends AppCompatActivity {

    private Toolbar baseBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ClientApplication.addActivity(this);
    }
    //Toolbar菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单文件
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void initBaseToolbar(String title){
        baseBar =(Toolbar) findViewById(R.id.student_main_toolbal);
        // baseBar.setLogo(R.drawable.wode);
        baseBar.setTitle(title);
//        baseBar.setSubtitle("课程列表");
        setSupportActionBar(baseBar);

        baseBar.setNavigationIcon(R.drawable.back);
        baseBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_student_main);

            }
        });
        baseBar.setOnMenuItemClickListener(new BaseToolBarMenuSelectedListener(this));

    }

}