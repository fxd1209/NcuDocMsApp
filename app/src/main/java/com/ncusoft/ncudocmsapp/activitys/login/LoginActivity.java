package com.ncusoft.ncudocmsapp.activitys.login;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.pojo.User;
import com.ncusoft.ncudocmsapp.repository.DatabaseHelper;
import com.ncusoft.ncudocmsapp.service.login.LoginInterface;
import com.ncusoft.ncudocmsapp.service.login.LoginService;
import com.ncusoft.ncudocmsapp.utils.SharedVar;
import com.ncusoft.ncudocmsapp.utils.ToastUtil;

public class LoginActivity extends AppCompatActivity {
    LoginInterface loginInterface=new LoginService();
    Button btnRegister;
    Button btnLogin;
    EditText loginId;
    EditText loginPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getApplication只能在Activity中调用，但是需要在service中调用，为避免传参，
        // 故将application保存在SharedVar里面
        ClientApplication clientApplication=(ClientApplication)getApplication();
        SharedVar.setClientApplication(clientApplication);

        btnRegister=(Button)findViewById(R.id.btn_register);
        btnLogin=(Button)findViewById(R.id.btn_login);
        loginId=(EditText)findViewById(R.id.edit_login_id);
        loginPwd=(EditText)findViewById(R.id.edit_login_password);

        //绑定监听
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=loginId.getText().toString();
                String pwd=loginPwd.getText().toString();
                if (id.equals("") || pwd.equals("")){
                    ToastUtil.initToast(LoginActivity.this, ToastUtil.ToastType.FAIL, "用户名或密码不能为空！",
                            Toast.LENGTH_SHORT,new Point(0,0)).show();
                    return ;
                }
                User user=loginInterface.login(id,pwd);
                if(user==null){
                    ToastUtil.initToast(LoginActivity.this, ToastUtil.ToastType.FAIL, "用户名或密码错误",
                            Toast.LENGTH_SHORT,new Point(0,0)).show();
                } else{
                    Intent intent=new Intent();
                    switch (user.getAuthority()){
                        case "ADMIN":
                            //TODO: 到管理员界面
                            break;
                        case "STUDENT":
                            //TODO: 到学生界面
                            break;
                        case "TEACHER":
                            //TODO: 到教师界面
                            break;
                    }
                    startActivity(intent);
                }


            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}

