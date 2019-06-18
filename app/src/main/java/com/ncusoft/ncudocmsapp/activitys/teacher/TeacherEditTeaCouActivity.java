package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;
import com.ncusoft.ncudocmsapp.service.course.CourseService;
import com.ncusoft.ncudocmsapp.service.course.CourseServiceInterface;
import com.ncusoft.ncudocmsapp.utils.ToastUtil;

public class TeacherEditTeaCouActivity extends BaseActivity {
    private EditText editCourseId;
    private EditText editCourseName;
    private EditText editCourseCredit;

    private Button btnEdit;
    private Button btnSave;

    private Course course;
    private CourseServiceInterface courseService;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_editcourse);
        initBaseToolbar("编辑课程");
        courseService=new CourseService();
        editCourseId=(EditText)findViewById(R.id.teacher_editcourse_ID);
        editCourseName=(EditText)findViewById(R.id.teacher_editcourse_name);
        editCourseCredit=(EditText)findViewById(R.id.teacher_editcourse_credit);
        editCourseId.setFocusable(false); //ID一直不可编辑
        editCourseName.setEnabled(false);
        editCourseCredit.setEnabled(false);
        btnEdit=(Button)findViewById(R.id.btn_teacher_editcourse_change);
        btnSave=(Button)findViewById(R.id.btn_teacher_editcourse_save);
        btnSave.setEnabled(false);
        btnEdit.setOnClickListener(onClickListener);
        btnSave.setOnClickListener(onClickListener);
        //TeacherCourseActivity传来的课程信息
        Intent intent=getIntent();
        TeacherCourse teacherCourse=(TeacherCourse)intent.getSerializableExtra("teacherCourse");
        course=teacherCourse.getCourse();
        if (course!=null){
            editCourseId.setText(course.getId());
            editCourseName.setText(course.getName());
            editCourseCredit.setText(course.getCredit());
        }else{course=new Course();}

    }
    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_teacher_editcourse_change:
                    //重新编辑
                    System.out.println("你好");
                    editCourseId.setText(course.getId());
                    editCourseName.setText(course.getName());
                    editCourseCredit.setText(course.getCredit());
                    editCourseName.setEnabled(true);
                    editCourseCredit.setEnabled(true);
                    btnSave.setEnabled(true);
                    break;
                case R.id.btn_teacher_editcourse_save:
                    Course course=new Course.CourseBuilder()
                            .id(editCourseId.getText().toString())
                            .name(editCourseName.getText().toString())
                            .credit(editCourseCredit.getText().toString())
                            .build();
                    String msg=courseService.inputCheck(course);
                    if (!msg.equals("SUCCESS")){
                        ToastUtil.initToast(TeacherEditTeaCouActivity.this, ToastUtil.ToastType.FAIL,
                                msg, Toast.LENGTH_LONG,new Point(0,0))
                                .show();
                        return;
                    }
                    if (0!=courseService.updateById(course)){
                        ToastUtil.initToast(TeacherEditTeaCouActivity.this, ToastUtil.ToastType.SUCCESS,
                                "更新成功", Toast.LENGTH_LONG,new Point(0,0))
                                .show();
                        editCourseName.setEnabled(false);
                        editCourseCredit.setEnabled(false);
                        btnSave.setEnabled(false);
                    }else {
                        ToastUtil.initToast(TeacherEditTeaCouActivity.this, ToastUtil.ToastType.SUCCESS,
                                "更新失败", Toast.LENGTH_LONG,new Point(0,0))
                                .show();
                    }

            }
        }
    };
}
