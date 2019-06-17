package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.os.Bundle;
import android.widget.EditText;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.pojo.Teacher;
import com.ncusoft.ncudocmsapp.service.user.TeacherService;
import com.ncusoft.ncudocmsapp.service.user.TeacherServiceInterface;

public class TeacherInfoActivity extends BaseActivity {

    private EditText editName;
    private EditText editSex;
    private EditText editPhone;
    private EditText editEmail;
    private EditText editProTitle;

    TeacherServiceInterface teacherService;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.teacher_info);
        initBaseToolbar("个人信息");

        editName=(EditText)findViewById(R.id.teacher_information_name);
        editSex=(EditText)findViewById(R.id.teacher_information_sex);
        editPhone=(EditText)findViewById(R.id.teacher_information_phonenumber);
        editEmail=(EditText)findViewById(R.id.teacher_information_email);
        editProTitle=(EditText)findViewById(R.id.teacher_information_protitle);
        teacherService=new TeacherService();
        Teacher teacher=teacherService.getUserInfo();
        editName.setText(teacher.getName());
        editSex.setText(teacher.getSex());
        editPhone.setText(teacher.getPhone());
        editEmail.setText(teacher.getEmail());
        editProTitle.setText(teacher.getProTitle());

    }
}
