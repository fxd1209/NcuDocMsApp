package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
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

    private Button btnInfoChange;

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

        btnInfoChange=(Button)findViewById(R.id.btn_teacher_info_change);

        teacherService=new TeacherService();
        Teacher teacher=teacherService.getUserInfo();
        editName.setText(teacher.getName());
        editSex.setText(teacher.getSex());
        editPhone.setText(teacher.getPhone());
        editEmail.setText(teacher.getEmail());
        editProTitle.setText(teacher.getProTitle());
        btnInfoChange.setOnClickListener(onClickListener);
    }
    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_teacher_info_change:
                    editName.setEnabled(true);
                    editSex.setEnabled(true);
                    editPhone.setEnabled(true);
                    editEmail.setEnabled(true);
                    editProTitle.setEnabled(true);
                    break;
            }
        }
    };
}
