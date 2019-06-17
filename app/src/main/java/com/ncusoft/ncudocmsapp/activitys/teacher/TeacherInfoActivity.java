package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.pojo.Teacher;
import com.ncusoft.ncudocmsapp.service.user.TeacherService;
import com.ncusoft.ncudocmsapp.service.user.TeacherServiceInterface;
import com.ncusoft.ncudocmsapp.utils.ToastUtil;
import com.ncusoft.ncudocmsapp.utils.VerifyUtil;

public class TeacherInfoActivity extends BaseActivity {

    private EditText editName;
    private EditText editSex;
    private EditText editPhone;
    private EditText editEmail;
    private EditText editProTitle;

    private Button btnInfoChange;
    private Button btnInfoSave;

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
        btnInfoSave=(Button)findViewById(R.id.btn_teacher_info_save);

        teacherService=new TeacherService();
        Teacher teacher=teacherService.getUserInfo();
        editName.setText(teacher.getName());
        editSex.setText(teacher.getSex());
        editPhone.setText(teacher.getPhone());
        editEmail.setText(teacher.getEmail());
        editProTitle.setText(teacher.getProTitle());
        btnInfoChange.setOnClickListener(onClickListener);
        btnInfoSave.setOnClickListener(onClickListener);
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
                    btnInfoChange.setEnabled(false);
                    btnInfoSave.setEnabled(true);
                    break;
                case R.id.btn_teacher_info_save:
                    String name=editName.getText().toString();
                    String sex=editSex.getText().toString();
                    String phone=editPhone.getText().toString();
                    String email=editEmail.getText().toString();
                    String protitle=editProTitle.getText().toString();
                    Teacher teacher=new Teacher.TeacherBuilder()
                            .id(ClientApplication.getInstance().getCurrentLoginUser().getId())
                            .name(name)
                            .sex(sex)
                            .phone(phone)
                            .email(email)
                            .proTitle(protitle)
                            .build();
                    String msg=teacherService.inputCheck(teacher);
                    if (!msg.equals("SUCCESS")){
                        ToastUtil.initToast(TeacherInfoActivity.this, ToastUtil.ToastType.FAIL,
                                msg, Toast.LENGTH_LONG,new Point(0,0))
                                .show();
                        return;
                    }
                    if(-1==teacherService.updateById(teacher)){
                        ToastUtil.initToast(TeacherInfoActivity.this, ToastUtil.ToastType.FAIL,
                                "保存失败", Toast.LENGTH_LONG,new Point(0,0))
                                .show();
                    }else{
                        ToastUtil.initToast(TeacherInfoActivity.this, ToastUtil.ToastType.SUCCESS,
                                "保存成功", Toast.LENGTH_LONG,new Point(0,0))
                                .show();
                        editName.setEnabled(false);
                        editSex.setEnabled(false);
                        editPhone.setEnabled(false);
                        editEmail.setEnabled(false);
                        editProTitle.setEnabled(false);
                        btnInfoChange.setEnabled(true);
                        btnInfoSave.setEnabled(false);
                    }



            }
        }
    };
}
