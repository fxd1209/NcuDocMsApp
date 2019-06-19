package com.ncusoft.ncudocmsapp.activitys.login;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.activitys.student.StudentMainActivity;
import com.ncusoft.ncudocmsapp.activitys.teacher.TeacherMainActivity;
import com.ncusoft.ncudocmsapp.pojo.User;
import com.ncusoft.ncudocmsapp.service.login.LoginServiceInterface;
import com.ncusoft.ncudocmsapp.service.login.LoginService;
import com.ncusoft.ncudocmsapp.utils.ToastUtil;

public class LoginActivity extends BaseActivity {
    private LoginServiceInterface loginInterface=new LoginService();
    private Button btnReg,btnLogin;/*,btnForgetPwd*/
    private EditText loginId,loginPwd;
    private CheckBox checkBoxRemPass;
    private LoginReceiver myReceiver;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myReceiver = new LoginReceiver();
        IntentFilter itFilter = new IntentFilter();
        itFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(myReceiver, itFilter);

        //getApplication只能在Activity中调用，需要在service中调用，
        // 故将application保存供调用
        ClientApplication.setClientApplication((ClientApplication)getApplication());

        btnReg=(Button)findViewById(R.id.btn_register);
        btnLogin=(Button)findViewById(R.id.btn_login);
//        btnForgetPwd=(Button)findViewById(R.id.btn_forget_pwd);
        loginId=(EditText)findViewById(R.id.edit_login_id);
        loginPwd=(EditText)findViewById(R.id.edit_login_password);
        checkBoxRemPass=(CheckBox)findViewById(R.id.login_remember_pass);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember=sharedPreferences.getBoolean("remember_password",false);
        //将值设置在输入框中
        if (isRemember){
            String userId=sharedPreferences.getString("userId","");
            String userPwd=sharedPreferences.getString("userPwd","");
            loginId.setText(userId);
            loginPwd.setText(userPwd);
            checkBoxRemPass.setChecked(true);
        }
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
                    isRememberPassword(user);
                    switch (user.getAuthority()){
                        case "ADMIN":
                            //TODO: 到管理员界面
                            break;
                        case "STUDENT":
                            //记录当前登录的信息
                            ClientApplication.getInstance().setCurrentLoginUser(user);
                            intent.setClass(LoginActivity.this, StudentMainActivity.class);
                            startActivity(intent);
                            break;
                        case "TEACHER":
                            //记录当前登录的信息
                            ClientApplication.getInstance().setCurrentLoginUser(user);
                            intent.setClass(LoginActivity.this, TeacherMainActivity.class);
                            startActivity(intent);
                            break;
                    }
                    startActivity(intent);
                    finish();
                }


            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
//        btnForgetPwd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent().setClass(LoginActivity.this, TeacherCourseActivity.class));
//            }
//        });


    }
    private long firstClickTime=0L;
    @Override
    public void onBackPressed(){
        if (System.currentTimeMillis()-firstClickTime<1500){
            finish();
            ClientApplication.exit();
            System.exit(0);
        }else {
            ToastUtil.initToast(LoginActivity.this, ToastUtil.ToastType.WARNING,
                    "双击退出",Toast.LENGTH_LONG,new Point(0,0)).show();
            firstClickTime=System.currentTimeMillis();

        }
    }
    //别忘了将广播取消掉哦~
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
    private void isRememberPassword(User user){
        editor=sharedPreferences.edit();
        if (checkBoxRemPass.isChecked()){
            editor.putBoolean("remember_password",true);
            editor.putString("userId",user.getId());
            editor.putString("userPwd",user.getPassword());
        }else{
            editor.clear();
        }
        editor.apply();
    }





}

