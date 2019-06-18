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
                Course course=new Course.CourseBuilder()
                        .id(courseId)
                        .name(courseName)
                        .credit(courseCredit)
                        .build();
                String msg=courseService.inputCheck(course);
                if (!msg.equals("SUCCESS")){
                    ToastUtil.initToast(AdminAddCourseActivity.this, ToastUtil.ToastType.FAIL,
                            msg,Toast.LENGTH_LONG,new Point(0,0))
                            .show();
                    return;
                }
                if (-1!=courseService.addCourse(course)){
                    String strTip="添加"+courseName+"("+courseId+")"+courseCredit+"学分成功!";
                    ToastUtil.initToast(AdminAddCourseActivity.this, ToastUtil.ToastType.SUCCESS,
                            strTip, Toast.LENGTH_LONG,new Point(0,0))
                            .show();
                    editCourseId.setText("");
                    editCourseName.setText("");
                    editCourseCredit.setText("");
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
