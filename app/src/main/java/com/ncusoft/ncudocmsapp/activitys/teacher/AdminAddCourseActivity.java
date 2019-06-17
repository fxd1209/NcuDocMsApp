package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.service.course.CourseService;
import com.ncusoft.ncudocmsapp.service.course.CourseServiceInterface;
import com.ncusoft.ncudocmsapp.utils.ToastUtil;
import com.ncusoft.ncudocmsapp.utils.VerifyUtil;

public class AdminAddCourseActivity extends BaseActivity {

    private CourseServiceInterface courseService;
    private Button btnAddCourse;
    private EditText editCourseId;
    private EditText editCourseName;
    private EditText editCourseCredit;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_addcourse);
        initBaseToolbar("添加课程");
        courseService=new CourseService();
        btnAddCourse=(Button)findViewById(R.id.btn_admin_addCourse_enter);
        editCourseId=(EditText)findViewById(R.id.adminAddCourseId);
        editCourseName=(EditText)findViewById(R.id.adminAddCourseName);
        editCourseCredit=(EditText)findViewById(R.id.adminAddCredit);
        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseId=editCourseId.getText().toString();
                String courseName=editCourseName.getText().toString();
                String courseCredit=editCourseCredit.getText().toString();
                if (VerifyUtil.isStrEmpty(courseId) ||
                        VerifyUtil.isStrEmpty(courseName) ||
                        VerifyUtil.isStrEmpty(courseCredit)){
                    ToastUtil.initToast(AdminAddCourseActivity.this, ToastUtil.ToastType.FAIL,
                            "有些输入不能为空!", Toast.LENGTH_LONG,new Point(0,0))
                            .show();
                    return;
                }else if (!VerifyUtil.isConsistsOfNum_Letter(courseId) || courseId.length()<6){
                    ToastUtil.initToast(AdminAddCourseActivity.this, ToastUtil.ToastType.FAIL,
                            "课程号为6位以上ASICC码!", Toast.LENGTH_LONG,new Point(0,0))
                            .show();
                    return;
                }else if(!VerifyUtil.isNum(courseCredit) || Float.parseFloat(courseCredit)<0){
                    ToastUtil.initToast(AdminAddCourseActivity.this, ToastUtil.ToastType.FAIL,
                            "学分不合法!", Toast.LENGTH_LONG,new Point(0,0))
                            .show();
                    return;
                }
                if (-1!=courseService.addCourse(new Course.CourseBuilder()
                        .id(courseId)
                        .name(courseName)
                        .credit(courseCredit)
                        .build())){
                    String strTip="添加"+courseName+"("+courseId+")"+courseCredit+"学分成功!";
                    ToastUtil.initToast(AdminAddCourseActivity.this, ToastUtil.ToastType.SUCCESS,
                            strTip, Toast.LENGTH_LONG,new Point(0,0))
                            .show();
                }else {
                    String strTip="添加"+courseName+"("+courseId+")"+courseCredit+"学分失败,可能该课程ID已存在!";
                    ToastUtil.initToast(AdminAddCourseActivity.this, ToastUtil.ToastType.FAIL,
                            strTip, Toast.LENGTH_LONG,new Point(0,0))
                            .show();
                }


            }
        });
    }
}
