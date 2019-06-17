package com.ncusoft.ncudocmsapp.repository.course;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.repository.DatabaseHelper;
import com.ncusoft.ncudocmsapp.repository.TableInterface;

import java.util.ArrayList;
import java.util.List;

public class CourseDao implements TableInterface {
    private static CourseDao dao;
    private static DatabaseHelper databaseHelper;
    //表名以及字段
    public final static String tableName = "course_table";
    public final static String id = "id";
    public final static String name = "name";
    public final static String credit = "credit";

    private CourseDao(){
        CourseDao.databaseHelper= ClientApplication.getDatabaseHelper();
    }
    public static CourseDao getInstance() {
        if (CourseDao.dao == null)
            dao = new CourseDao();
        return dao;
    }
    @Override
    public void onCreateTable(SQLiteDatabase db) {
        String sql = "Create table if not exists " + CourseDao.tableName + "(" + CourseDao.id + " TEXT primary key,"
                + CourseDao.name + " TEXT,"
                + CourseDao.credit + " TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgradeTable(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public long insert(DatabaseHelper databaseHelper, ContentValues values) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long id = db.insert(CourseDao.tableName, null, values);
        db.close();
        return id;
    }
    @Override
    public long insert(ContentValues values) {
        return insert(databaseHelper,values);
    }

    @Override
    public int delete(String table, String whereClause, String[] whereArgs) {
        return 0;
    }

    public Course queryById(String id) {
       return queryById(databaseHelper,id);
    }
    @Override
    public Course queryById(DatabaseHelper databaseHelper, String id) {
        String sql = "select * from " + CourseDao.tableName + " where " + CourseDao.id + "=?";
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{id});
        //若查询到结果
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) { //后面条件满足一条即可
            Course course = cursorToPojo(cursor);
            cursor.close();
            db.close();
            return course;
        }
        //如果查询到的结果为空
        return null;
    }
    public List<Course> queryAll() {
        return queryAll(databaseHelper);
    }
    @Override
    public List<Course> queryAll(DatabaseHelper databaseHelper) {
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        List<Course> list=new ArrayList<>();
//        Cursor cursor = db.rawQuery("select * from person where name like ? and age=?", new String[]{"%传智%", "4"});
//        Cursor cursor = db.rawQuery(“select * from person”, null);
        Cursor cursor=db.query(
                CourseDao.tableName, null, null, null,
                null, null, null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            list.add(cursorToPojo(cursor));
        }
        cursor.close();
        db.close();
        return list;
    }

    @Override
    public long update(DatabaseHelper databaseHelper, ContentValues contentValues) {
        return 0;
    }

    @Override
    public long update(ContentValues contentValues) {
        return 0;
    }

    private Course cursorToPojo(Cursor cursor){
        return new Course.CourseBuilder()
                .id(cursor.getString(cursor.getColumnIndex(CourseDao.id)))
                .name(cursor.getString(cursor.getColumnIndex(CourseDao.name)))
                .credit(cursor.getString(cursor.getColumnIndex(CourseDao.credit)))
                .build();
    }
}
