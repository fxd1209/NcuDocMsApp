package com.ncusoft.ncudocmsapp.activitys.login;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.pojo.Student;
import com.ncusoft.ncudocmsapp.pojo.Teacher;
import com.ncusoft.ncudocmsapp.service.login.RegisterServiceInterface;
import com.ncusoft.ncudocmsapp.service.login.RegisterService;
import com.ncusoft.ncudocmsapp.utils.ToastUtil;

public class RegisterActivity extends AppCompatActivity {

    RegisterServiceInterface registerInterface=new RegisterService();

    EditText regId,regName,regPwd,checkPwd;
    Button btnReg,btnBack;
    Spinner spinnerAuthority;
    RadioGroup radioGroupSex;

    String sex="男";  //布局文件中性别默认选中男
    String authority="学生";  //布局文件中身份默认选中学生


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_register);

        ClientApplication clientApplication=(ClientApplication)getApplication();


        regId=(EditText)findViewById(R.id.edit_reg_id);
        regName=(EditText)findViewById(R.id.edit_reg_name);
        regPwd=(EditText)findViewById(R.id.edit_reg_pwd);
        checkPwd=(EditText)findViewById(R.id.edit_reg_checkpwd);
        spinnerAuthority=(Spinner)findViewById(R.id.spinner_reg_authority);
        radioGroupSex=(RadioGroup) findViewById(R.id.radio_sex);
        btnReg=(Button)findViewById(R.id.btn_reg);
        btnBack=(Button)findViewById(R.id.btn_back);

        btnReg.setOnClickListener(new BtnOnClickListener());
        btnBack.setOnClickListener(new BtnOnClickListener());

        spinnerAuthority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                authority=parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
                case R.id.btn_reg:{
                    String id=regId.getText().toString();
                    String pwd=regPwd.getText().toString();
                    String name=regName.getText().toString();
                    String check=checkPwd.getText().toString();

                    if (!pwd.equals(check)){
                        ToastUtil.initToast(RegisterActivity.this,
                                ToastUtil.ToastType.FAIL,"两次密码不一致!",
                                Toast.LENGTH_LONG,new Point(0,0)).show();
                        return;
                    }
                    if(authority.equals("学生")){
                        Student student=new Student.StudentBuilder()
                                .id(id)
                                .name(name)
                                .password(pwd)
                                .sex(sex)
                                .phone(id).authority("STUDENT").build();
                        String msg=registerInterface.inputCheck(student);

                        if (msg.equals("SUCCESS")){
                            if(registerInterface.addStudent(student)){
                                ToastUtil.initToast(RegisterActivity.this,
                                        ToastUtil.ToastType.SUCCESS,"注册学生成功",
                                        Toast.LENGTH_LONG,new Point(0,0)).show();
                                startActivity(new Intent().setClass(RegisterActivity.this,LoginActivity.class));
                            }
                            else{
                                ToastUtil.initToast(RegisterActivity.this,
                                        ToastUtil.ToastType.SUCCESS,"注册学生失败!可能用户已存在!请重试!",
                                        Toast.LENGTH_LONG,new Point(0,0)).show();
                            }
                        }else{
                            ToastUtil.initToast(RegisterActivity.this,
                                    ToastUtil.ToastType.FAIL,msg,
                                    Toast.LENGTH_LONG,new Point(0,0)).show();
                        }
                    }else{
                        Teacher teacher=new Teacher.TeacherBuilder()
                                .id(id)
                                .name(name)
                                .password(pwd)
                                .sex(sex)
                                .phone(id).authority("TEACHER").build();
                        String msg=registerInterface.inputCheck(teacher);

                        if (msg.equals("SUCCESS")){
                            if(registerInterface.addTeacher(teacher)){
                                ToastUtil.initToast(RegisterActivity.this,
                                        ToastUtil.ToastType.SUCCESS,"注册教师成功",
                                        Toast.LENGTH_LONG,new Point(0,0)).show();
                                startActivity(new Intent().setClass(RegisterActivity.this,LoginActivity.class));
                            }
                            else{
                                ToastUtil.initToast(RegisterActivity.this,
                                        ToastUtil.ToastType.SUCCESS,"注册教师失败!可能用户已存在!请重试!",
                                        Toast.LENGTH_LONG,new Point(0,0)).show();
                            }
                        }else{
                            ToastUtil.initToast(RegisterActivity.this,
                                    ToastUtil.ToastType.FAIL,msg,
                                    Toast.LENGTH_LONG,new Point(0,0)).show();
                        }
                    }
                    break;
                }
                case R.id.btn_back:{
                    Intent intent=new Intent();
                    intent.setClass(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        }
    }
}
