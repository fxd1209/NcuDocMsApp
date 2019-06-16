package com.ncusoft.ncudocmsapp.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ncusoft.ncudocmsapp.repository.course.CourseDao;
import com.ncusoft.ncudocmsapp.repository.course.TeacherCourseDao;
import com.ncusoft.ncudocmsapp.repository.login.UserDao;
import com.ncusoft.ncudocmsapp.repository.teacher.TeacherDao;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表格
        UserDao.getInstance().onCreateTable(db);
        TeacherDao.getInstance().onCreateTable(db);
        CourseDao.getInstance().onCreateTable(db);
        TeacherCourseDao.getInstance().onCreateTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
