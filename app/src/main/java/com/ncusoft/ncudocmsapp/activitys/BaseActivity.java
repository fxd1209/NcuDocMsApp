package com.ncusoft.ncudocmsapp.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ncusoft.ncudocmsapp.ClientApplication;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ClientApplication.addActivity(this);
    }



}
