package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.repository.course.CourseDao;
import com.ncusoft.ncudocmsapp.repository.course.TeacherCourseDao;
import com.ncusoft.ncudocmsapp.service.user.TeacherService;
import com.ncusoft.ncudocmsapp.service.user.TeacherServiceInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TeacherSelectCourse extends Activity {
        private GridView gridView;
        private List<Map<String, Object>> dataList;
        private SimpleAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_teacher_selectcourse);
            gridView = (GridView) findViewById(R.id.gridview);
            //初始化数据
            initData();

            String[] from={"img","text"};

            int[] to={R.id.img,R.id.text};

            adapter=new SimpleAdapter(this, dataList, R.layout.course_select_grid, from, to);

            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    AlertDialog.Builder builder= new AlertDialog.Builder(TeacherSelectCourse.this);
                    builder.setTitle("提示").setMessage(dataList.get(arg2).get("text").toString()).create().show();
                }
            });
        }

        void initData() {
            //图标
            int icno[] = { R.drawable.dianzan, R.drawable.dianzan, R.drawable.dianzan,
                    R.drawable.dianzan, R.drawable.dianzan, R.drawable.dianzan, R.drawable.dianzan,
                    R.drawable.dianzan, R.drawable.dianzan, R.drawable.dianzan, R.drawable.dianzan, R.drawable.dianzan };
            //图标下的文字
            String name[]={"时钟","信号","宝箱","秒钟","大象","FF","记事本","书签","印象","商店","主题","迅雷"};
            dataList = new ArrayList<Map<String, Object>>();
            for (int i = 0; i <icno.length; i++) {
                Map<String, Object> map=new HashMap<String, Object>();
                map.put("img", icno[i]);
                map.put("text",name[i]);
                dataList.add(map);
            }
        }

    }

