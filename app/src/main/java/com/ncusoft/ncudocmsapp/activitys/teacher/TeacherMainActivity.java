package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;

public class TeacherMainActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_base,menu);
        return true;
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

}


