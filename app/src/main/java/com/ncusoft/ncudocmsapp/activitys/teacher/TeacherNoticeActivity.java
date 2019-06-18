package com.ncusoft.ncudocmsapp.activitys.teacher;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.BaseActivity;
import com.ncusoft.ncudocmsapp.service.downloadservice.DownloadService;

public class TeacherNoticeActivity extends BaseActivity{
    private Button btnDownloadImg;
    private Button btnPauseDownload;
    private Button btnCancelDownload;


    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder=(DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_notice);
        initBaseToolbar("信息通知");

        btnDownloadImg=findViewById(R.id.btn_teacher_downloadimg);
        btnPauseDownload=findViewById(R.id.btn_teacher_pausedownload);
        btnCancelDownload=findViewById(R.id.btn_teacher_canceldownload);
        btnDownloadImg.setOnClickListener(onClickListener);
        btnPauseDownload.setOnClickListener(onClickListener);
        btnCancelDownload.setOnClickListener(onClickListener);
        Intent intent=new Intent(TeacherNoticeActivity.this, DownloadService.class);
        startService(intent); //启动服务
        bindService(intent,connection,BIND_AUTO_CREATE); //绑定服务
        if (ContextCompat.checkSelfPermission(TeacherNoticeActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(TeacherNoticeActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    private View.OnClickListener onClickListener =new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (downloadBinder==null){
                return;
            }
            switch (v.getId()){
                case R.id.btn_teacher_downloadimg:
                    System.out.println("开始");
//
//                    String url="https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
                    String url="https://dldir1.qq.com/qqfile/qq/PCQQ9.1.3/25332/QQ9.1.3.25332.exe";
                    downloadBinder.startDownload(url);

                    break;
                case R.id.btn_teacher_pausedownload:
                    System.out.println("暂停");
                    downloadBinder.pauseDownload();
                    break;
                case R.id.btn_teacher_canceldownload:
                    System.out.println("取消");
                    downloadBinder.cancelDownload();
                    break;
                default:break;
            }
        }
    };
//    @Override
//    public void onRequestPermissionResult(int requestCode,String[] permissions,int[] grantResults){
//
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
