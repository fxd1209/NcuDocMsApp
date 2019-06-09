package com.ncusoft.ncudocmsapp;

import android.app.Application;

import com.ncusoft.ncudocmsapp.repository.DatabaseHelper;

public class ClientApplication extends Application {

    private static ClientApplication clientApplication;
    private static DatabaseHelper databaseHelper;
    private static String DatabaseName="ncudms.db";

    /**
     * android.app.Application包的onCreate（）才是真正的Android程序的入口点
     */
    @Override
    public void onCreate(){
        super.onCreate();
        ClientApplication.databaseHelper=new DatabaseHelper(getApplicationContext(),DatabaseName,null,1);
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
}
