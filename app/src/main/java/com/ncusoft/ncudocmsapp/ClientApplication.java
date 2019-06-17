package com.ncusoft.ncudocmsapp;

import android.app.Activity;
import android.app.Application;

import com.ncusoft.ncudocmsapp.pojo.User;
import com.ncusoft.ncudocmsapp.repository.DatabaseHelper;

import java.util.LinkedList;
import java.util.List;

public class ClientApplication extends Application {

    private static List<Activity> activityList = new LinkedList<Activity>();
    private static ClientApplication clientApplication;
    private static DatabaseHelper databaseHelper;
    private static String DatabaseName="ncudms.db";
    private User currentLoginUser;

    /**
     * android.app.Application包的onCreate（）才是真正的Android程序的入口点
     */
    @Override
    public void onCreate(){
        super.onCreate();
        ClientApplication.databaseHelper=new DatabaseHelper(getApplicationContext(),DatabaseName,null,1);
    }

    public User getCurrentLoginUser(){
        return currentLoginUser;
    }
    public void setCurrentLoginUser(User currentLoginUser){
        this.currentLoginUser=currentLoginUser;
    }
    public static DatabaseHelper getDatabaseHelper(){
        return ClientApplication.databaseHelper;
    }
    public static ClientApplication getInstance(){
        return clientApplication;
    }
    public static void setClientApplication(ClientApplication clientApplication){
        ClientApplication.clientApplication=clientApplication;
    }
    //添加Activity到容器中
    public static void addActivity(Activity activity)  {
        activityList.add(activity);
    }
    //遍历所有Activity并finish
    public static void exit() {
        for(Activity activity:activityList) {
            activity.finish();
        }
        activityList.clear();
    }
}
