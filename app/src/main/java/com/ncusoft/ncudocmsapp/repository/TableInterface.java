package com.ncusoft.ncudocmsapp.repository;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;

public interface TableInterface {
    public void onCreateTable(SQLiteDatabase db);
    public void onUpgradeTable(SQLiteDatabase db,int oldVersion,int newVersion);

    public long insert(DatabaseHelper databaseHelper, ContentValues values);
    //返回删除的数据条数
    public int delete(String table, String whereClause, String[] whereArgs);
    //通过ID
    public Object queryById(DatabaseHelper databaseHelper,String id);
    public List<?> queryAll(DatabaseHelper databaseHelper);

}
