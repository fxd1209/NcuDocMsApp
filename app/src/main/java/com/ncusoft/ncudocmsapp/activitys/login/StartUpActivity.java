package com.ncusoft.ncudocmsapp.activitys.login;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.utils.ToastUtil;

public class StartUpActivity extends BaseActivity implements View.OnClickListener{
    private Button btnLogin;
    private Button btnAbout;

    @Override
    public void onCreate(Bundle saveInstanceStat){
        super.onCreate(saveInstanceStat);
        setContentView(R.layout.activity_start_up);
        btnLogin=(Button)findViewById(R.id.startup_login);
        btnAbout=(Button)findViewById(R.id.startup_about);

        btnLogin.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startup_login:
                startActivity(new Intent().setClass(StartUpActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.startup_about:
                startActivity(new Intent().setClass(StartUpActivity.this,AboutInfoActivity.class));
                finish();
                break;
        }
    }
    private long firstClickTime=0L;
    @Override
    public void onBackPressed(){
        if (System.currentTimeMillis()-firstClickTime<1500){
            finish();
            ClientApplication.exit();
            System.exit(0);
        }else {
            ToastUtil.initToast(StartUpActivity.this, ToastUtil.ToastType.WARNING,
                    "双击退出", Toast.LENGTH_LONG,new Point(0,0)).show();
            firstClickTime=System.currentTimeMillis();

        }
    }
}
