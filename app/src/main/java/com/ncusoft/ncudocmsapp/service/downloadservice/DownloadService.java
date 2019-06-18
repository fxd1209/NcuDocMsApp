package com.ncusoft.ncudocmsapp.service.downloadservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.activitys.teacher.TeacherNoticeActivity;
import com.ncusoft.ncudocmsapp.utils.ToastUtil;

import java.io.File;

public class DownloadService extends Service{
    private DownloadTask downloadTask;
    private String downloadUrl;
    private DownloadListener listener=new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1,getNotification("下载中.....",progress));
        }

        @Override
        public void onSuccess() {
            downloadTask=null;
            //下载成功时将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("下载完成",-1));
//            ToastUtil.initToast(DownloadService.this, ToastUtil.ToastType.SUCCESS,
//                    "下载完成", Toast.LENGTH_LONG,new Point(0,0)).show();
            Toast.makeText(DownloadService.this,
                    "下载完成",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFaild() {
            downloadTask=null;
            //下载成功时将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("下载失败",-1));
//            ToastUtil.initToast(DownloadService.this, ToastUtil.ToastType.FAIL,
//                    "下载失败", Toast.LENGTH_LONG,new Point(0,0)).show();
            Toast.makeText(DownloadService.this,
                    "下载失败",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            downloadTask=null;
//            ToastUtil.initToast(DownloadService.this, ToastUtil.ToastType.FAIL,
//                    "暂停", Toast.LENGTH_LONG,new Point(0,0)).show();
            Toast.makeText(DownloadService.this,
                    "暂停",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCanceled() {
            downloadTask=null;
            stopForeground(true);
//            ToastUtil.initToast(DownloadService.this, ToastUtil.ToastType.FAIL,
//                    "取消", Toast.LENGTH_LONG,new Point(0,0)).show();
            Toast.makeText(DownloadService.this,
                    "取消",Toast.LENGTH_LONG).show();
        }
    };
    private DownloadBinder mBinder=new DownloadBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public class DownloadBinder extends Binder{
        public void startDownload(String url){
            if (downloadTask==null){
                downloadUrl=url;
                downloadTask=new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                startForeground(1,getNotification("下载中....",0));
//                ToastUtil.initToast(DownloadService.this, ToastUtil.ToastType.DOUBT,
//                        "下载中",Toast.LENGTH_LONG,new Point(0,0)).show();
                Toast.makeText(DownloadService.this,
                        "下载中",Toast.LENGTH_LONG).show();
            }
        }
        public void pauseDownload(){
            if (downloadTask!=null){
                downloadTask.pauseDownload();
            }
        }
        public void cancelDownload(){
            if (downloadTask!=null){
                downloadTask.cancelDownload();
            }else {
                if (downloadUrl!=null){
                    //取消下载时，将文件删除，并将通知关闭
                    String fileName=downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory= Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file=new File(directory+fileName);
                    if (file.exists()){
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
//                    ToastUtil.initToast(DownloadService.this, ToastUtil.ToastType.DOUBT,
//                            "取消成功",Toast.LENGTH_LONG,new Point(0,0)).show();
                    Toast.makeText(DownloadService.this,
                            "取消成功",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    private NotificationManager getNotificationManager(){
        return (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }
    private Notification getNotification(String title,int progress){
        Intent intent =new Intent(this, TeacherNoticeActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);

        String channelId = "ncuChannelId";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelName = "56NAME";
            NotificationChannel chan = new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW);
            chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            NotificationManager nm = getNotificationManager();
            nm.createNotificationChannel(chan);
        }

        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(this,channelId);
        if (progress>0){
            //当progress大于或等于0的时候才显示下载进度
            notificationBuilder.setContentText(progress+"%");
            notificationBuilder.setProgress(100,progress,false);
        }
        Notification notification = notificationBuilder.setOngoing(true)
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pi)
                .setContentTitle(title)
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        return notification;





//        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
//        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
//        //TODO:此处有个设置大图标
//        builder.setContentIntent(pi);
//        builder.setContentTitle(title);
//        if (progress>0){
//            //当progress大于或等于0的时候才显示下载进度
//            builder.setContentText(progress+"%");
//            builder.setProgress(100,progress,false);
//        }
//        return builder.build();
    }
}
