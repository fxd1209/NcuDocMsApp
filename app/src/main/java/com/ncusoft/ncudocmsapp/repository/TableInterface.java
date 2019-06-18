package com.ncusoft.ncudocmsapp.repository;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;

public interface TableInterface {
    public void onCreateTable(SQLiteDatabase db);
    public void onUpgradeTable(SQLiteDatabase db,int oldVersion,int newVersion);

    public long insert(DatabaseHelper databaseHelper, ContentValues values);
    public long insert(ContentValues values);

    //通过ID
    public Object queryById(DatabaseHelper databaseHelper,String id);
    public List<?> queryAll(DatabaseHelper databaseHelper);

    /**
     * 更新数据
     * @param databaseHelper
     * @param contentValues
     * @return
     */
    public long update(DatabaseHelper databaseHelper,ContentValues contentValues);
    public long update(ContentValues contentValues);

    /**
     * 删除数据
     */
    //返回删除的数据条数
    public int delete(DatabaseHelper databaseHelper,String table, String whereClause, String[] whereArgs);
    public int deleteById(DatabaseHelper databaseHelper,String id);
    public int deleteById(String id);

}
