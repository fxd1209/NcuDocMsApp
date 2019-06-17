package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.utils.ToastUtil;

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
                context.startActivity(new Intent().setClass(context,TeacherCourseActivity.class));
                break;
            case R.id.menu_teacher_selectclass: //选择课程
                context.startActivity(new Intent().setClass(context,TeacherSelectCouActivity.class));
                break;
            case R.id.menu_teacher_addclass:  //添加课程
                context.startActivity(new Intent().setClass(context,AdminAddCourseActivity.class));
                break;
            case R.id.menu_teacher_mymessage: //个人信息
                context.startActivity(new Intent().setClass(context,TeacherInfoActivity.class));
                break;
            case R.id.menu_teacher_notice: //通知
                ToastUtil.initToast((Activity)context, ToastUtil.ToastType.FAIL,
                        "你点击了通知，但是还在开发中!",
                        Toast.LENGTH_SHORT,new Point(0,0)).show();
                break;
            case R.id.menu_teacher_appraise: //评价
                ToastUtil.initToast((Activity)context, ToastUtil.ToastType.FAIL,
                        "你点击了评价，但是还在开发中!",
                        Toast.LENGTH_SHORT,new Point(0,0)).show();
                break;
        }
        return true;
    }
}
