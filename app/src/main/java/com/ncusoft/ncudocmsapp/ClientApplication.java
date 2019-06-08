package com.ncusoft.ncudocmsapp;

import android.app.Application;

import com.ncusoft.ncudocmsapp.repository.DatabaseHelper;

public class ClientApplication extends Application {

    private DatabaseHelper databaseHelper;
    private static String DatabaseName="ncudms.db";

    /**
     * android.app.Application包的onCreate（）才是真正的Android程序的入口点
     */
    @Override
    public void onCreate(){
        super.onCreate();
        this.databaseHelper=new DatabaseHelper(getApplicationContext(),DatabaseName,null,1);
    }

    public DatabaseHelper getDatabaseHelper(){
        if (this.databaseHelper==null)
            this.databaseHelper=new DatabaseHelper(getApplicationContext(),DatabaseName,null,1);
        return this.databaseHelper;
    }
}
