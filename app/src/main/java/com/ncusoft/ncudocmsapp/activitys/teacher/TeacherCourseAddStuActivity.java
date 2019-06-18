package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.activitys.login.LoginActivity;
import com.ncusoft.ncudocmsapp.activitys.login.RegisterActivity;
import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.Student;
import com.ncusoft.ncudocmsapp.pojo.StudentCourse;
import com.ncusoft.ncudocmsapp.pojo.Teacher;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;
import com.ncusoft.ncudocmsapp.service.login.RegisterService;
import com.ncusoft.ncudocmsapp.service.login.RegisterServiceInterface;
import com.ncusoft.ncudocmsapp.service.user.StudentService;
import com.ncusoft.ncudocmsapp.service.user.StudentServiceInterface;
import com.ncusoft.ncudocmsapp.utils.ToastUtil;

public class TeacherCourseAddStuActivity extends BaseActivity {
    private String term;
    private String courseId;
    private String classCount;

    EditText editStuId,editStuName,editStuEmail,editClassId;
    Button btnReset,btnSave;
    RadioGroup radioGroupSex;

    String sex="男";  //布局文件中性别默认选中男


    RegisterServiceInterface registerService;
    StudentServiceInterface studentService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_stuadd);
        initBaseToolbar("添加学生");
        registerService=new RegisterService();
        studentService=new StudentService();
        //TeacherCourseActivity传来的课程信息
        Intent intent=getIntent();
        TeacherCourse teacherCourse=(TeacherCourse)intent.getSerializableExtra("teacherCourse");
        term=teacherCourse.getTerm();
        courseId=teacherCourse.getCourseId();
        classCount=teacherCourse.getClassCount();

        editStuId=(EditText)findViewById(R.id.teacher_addstu_phonenumber);
        editStuName=(EditText)findViewById(R.id.teacher_addstu_name);
        editStuEmail=(EditText)findViewById(R.id.teacher_addstu_email);
        editClassId=(EditText)findViewById(R.id.teacher_addstu_classnumber);

        radioGroupSex=(RadioGroup) findViewById(R.id.teacher_addstu_sex);
        btnReset=(Button)findViewById(R.id.btn_teacher_addstu_change);
        btnSave=(Button)findViewById(R.id.btn_teacher_addstu_save);

        btnReset.setOnClickListener(new TeacherCourseAddStuActivity.BtnOnClickListener());
        btnSave.setOnClickListener(new TeacherCourseAddStuActivity.BtnOnClickListener());

        radioGroupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButtonSex=(RadioButton)findViewById(checkedId);
                sex=radioButtonSex.getText().toString();
            }
        });

    }

    class BtnOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_teacher_addstu_change:{
                    editStuId.setText("");
                    editStuName.setText("");
                    editStuEmail.setText("");
                    editClassId.setText("");
                    break;
                }
                case R.id.btn_teacher_addstu_save:{
                    String stuId=editStuId.getText().toString();
                    String stuName=editStuName.getText().toString();
                    String stuEmail=editStuEmail.getText().toString();
                    Student student=new Student.StudentBuilder()
                            .id(stuId)
                            .name(stuName)
                            .password("123456")
                            .sex(sex)
                            .phone(stuId).authority("STUDENT").build();
                    StudentCourse studentCourse=new StudentCourse.StudentCourseBuilder()
                            .id(null)
                            .studentId(stuId)
                            .courseId(courseId)
                            .classCount(classCount)
                            .term(term).build();
                    String msg=registerService.inputCheck(student);

                    if (msg.equals("SUCCESS")){
                        //TODO:此处应该用事务
                        if(registerService.addStudent(student)&&studentService.selectCourse(studentCourse)){
                            ToastUtil.initToast(TeacherCourseAddStuActivity.this,
                                    ToastUtil.ToastType.SUCCESS,"添加学生成功,密码为123456",
                                    Toast.LENGTH_LONG,new Point(0,0)).show();
                            onBackPressed();
                        } else{
                            ToastUtil.initToast(TeacherCourseAddStuActivity.this,
                                    ToastUtil.ToastType.SUCCESS,"添加学生失败!可能已存在!请重试!",
                                    Toast.LENGTH_LONG,new Point(0,0)).show();
                        }
                    }else{
                        ToastUtil.initToast(TeacherCourseAddStuActivity.this,
                                ToastUtil.ToastType.FAIL,msg, Toast.LENGTH_LONG,new Point(0,0)).show();
                    }
                    break;
                }
            }
        }
    }
}
