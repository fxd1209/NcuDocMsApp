package com.ncusoft.ncudocmsapp.repository.teacher;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.pojo.Teacher;
import com.ncusoft.ncudocmsapp.pojo.User;
import com.ncusoft.ncudocmsapp.repository.DatabaseHelper;
import com.ncusoft.ncudocmsapp.repository.TableInterface;
import com.ncusoft.ncudocmsapp.repository.login.UserDao;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings(value = "uncheck")
public class TeacherDao implements TableInterface {

    private static TeacherDao teacherDao;
    private static DatabaseHelper databaseHelper;
    //表名以及字段
    public final static String tableName = "teacher_table";
    public final static String id = "id";
    public final static String name = "name";
    public final static String sex = "sex";
    public final static String phone = "phone";
    public final static String email = "email";
    public final static String proTitle = "proTitle";

    private TeacherDao(){
        TeacherDao.databaseHelper= ClientApplication.getDatabaseHelper();
    }
    public static TeacherDao getInstance() {
        if (TeacherDao.teacherDao == null)
            teacherDao = new TeacherDao();
        return teacherDao;
    }
    @Override
    public void onCreateTable(SQLiteDatabase db) {
        String sql = "Create table if not exists " + TeacherDao.tableName + "(" + TeacherDao.id + " TEXT primary key,"
                + TeacherDao.name + " TEXT,"
                + TeacherDao.sex + " TEXT,"
                + TeacherDao.phone + " TEXT,"
                + TeacherDao.email + " TEXT,"
                + TeacherDao.proTitle + " TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgradeTable(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public long insert(DatabaseHelper databaseHelper, ContentValues values) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long id = db.insert(TeacherDao.tableName, null, values);
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

    public Teacher queryById(String id) {
        return queryById(databaseHelper,id);
    }
    @Override
    public Teacher queryById(DatabaseHelper databaseHelper, String id) {
        String sql = "select * from " + TeacherDao.tableName + " where " + TeacherDao.id + "=?";
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{id});
        //若查询到结果
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) { //后面条件满足一条即可
            Teacher teacher = cursorToPojo(cursor);
            cursor.close();
            db.close();
            return teacher;
        }
        //如果查询到的结果为空
        return null;
    }

    @Override
    public List<Teacher> queryAll(DatabaseHelper databaseHelper) {
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        List<Teacher> list=new ArrayList<>();
//        Cursor cursor = db.rawQuery("select * from person where name like ? and age=?", new String[]{"%传智%", "4"});
//        Cursor cursor = db.rawQuery(“select * from person”, null);
        Cursor cursor=db.query(
                TeacherDao.tableName, null, null, null,
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
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long id=db.update(TeacherDao.tableName, contentValues, TeacherDao.id+"=?", new String[]{contentValues.getAsString(TeacherDao.id)});
        db.close();
        return id;
    }

    @Override
    public long update(ContentValues contentValues) {
        return update(databaseHelper,contentValues);
    }

    private Teacher cursorToPojo(Cursor cursor){
        Teacher teacher=new Teacher();
        teacher.setId(cursor.getString(cursor.getColumnIndex(TeacherDao.id)));
        teacher.setName(cursor.getString(cursor.getColumnIndex(TeacherDao.name)));
        teacher.setSex(cursor.getString(cursor.getColumnIndex(TeacherDao.sex)));
        teacher.setPhone(cursor.getString(cursor.getColumnIndex(TeacherDao.phone)));
        teacher.setEmail(cursor.getString(cursor.getColumnIndex(TeacherDao.email)));
        teacher.setProTitle(cursor.getString(cursor.getColumnIndex(TeacherDao.proTitle)));
        return teacher;
    }


}
