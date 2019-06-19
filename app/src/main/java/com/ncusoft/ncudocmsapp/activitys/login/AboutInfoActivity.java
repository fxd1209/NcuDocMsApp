package com.ncusoft.ncudocmsapp.activitys.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;

public class AboutInfoActivity extends BaseActivity implements View.OnClickListener{

    private Button btnBack;

    @Override
    public void onCreate(Bundle saveInstanceStat){
        super.onCreate(saveInstanceStat);
        setContentView(R.layout.about_information);
        btnBack=(Button)findViewById(R.id.about_information_back);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.about_information_back:
                startActivity(new Intent().setClass(AboutInfoActivity.this, StartUpActivity.class));
                finish();
                break;
        }
    }
}
