package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.R;

public class BaseToolBarMenuSelectedListener implements Toolbar.OnMenuItemClickListener{
    private Context context;
    public BaseToolBarMenuSelectedListener(Context context)
    {
        this.context=context;
    }
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){//
            // case R.id.action_search://因为使用android.support.v7.widget.SearchView类，可以在
            // onCreateOptionsMenu(Menu menu)中直接设置监听事件//
            // Snackbar.make(toolbar,"Click Search",Snackbar.LENGTH_SHORT).show();//
            // break;
            case R.id.menu_teacher_myclass: //我的课程
                Toast.makeText(context, "This is a Toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_teacher_selectclass: //选择课程
                Toast.makeText(context, "This is a Toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_teacher_addclass:  //添加课程
                Toast.makeText(context, "This is a Toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_teacher_mymessage: //个人信息
                Toast.makeText(context, "This is a Toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_teacher_notice: //通知
                Toast.makeText(context, "This is a Toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_teacher_appraise: //评价
                Toast.makeText(context, "This is a Toast", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
