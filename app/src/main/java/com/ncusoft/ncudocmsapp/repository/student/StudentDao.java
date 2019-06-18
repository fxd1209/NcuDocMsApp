package com.ncusoft.ncudocmsapp.repository.student;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.pojo.Student;
import com.ncusoft.ncudocmsapp.repository.DatabaseHelper;
import com.ncusoft.ncudocmsapp.repository.TableInterface;

import java.util.ArrayList;
import java.util.List;

public class StudentDao implements TableInterface {

    private static StudentDao studentDao;
    private static DatabaseHelper databaseHelper;
    //表名以及字段
    public final static String tableName = "student_table";
    public final static String id = "id";
    public final static String name = "name";
    public final static String sex = "sex";
    public final static String grade="grade";
    public final static String classId="classId";
    public final static String phone = "phone";
    public final static String email = "email";

    private StudentDao(){
        StudentDao.databaseHelper= ClientApplication.getDatabaseHelper();
    }
    public static StudentDao getInstance() {
        if (StudentDao.studentDao == null)
            studentDao = new StudentDao();
        return studentDao;
    }
    @Override
    public void onCreateTable(SQLiteDatabase db) {
        String sql = "Create table if not exists " + StudentDao.tableName + "(" + StudentDao.id + " TEXT primary key,"
                + StudentDao.name + " TEXT,"
                + StudentDao.sex + " TEXT,"
                + StudentDao.grade + " TEXT,"
                + StudentDao.classId + " TEXT,"
                + StudentDao.phone + " TEXT,"
                + StudentDao.email + " TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgradeTable(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public long insert(DatabaseHelper databaseHelper, ContentValues values) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long id = db.insert(StudentDao.tableName, null, values);
        db.close();
        return id;
    }
    @Override
    public long insert(ContentValues values) {
        return insert(databaseHelper,values);
    }

    @Override
    public int delete(DatabaseHelper databaseHelper,String table, String whereClause, String[] whereArgs) {
        return 0;
    }

    @Override
    public int deleteById(DatabaseHelper databaseHelper, String id) {
        return 0;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    public Student queryById(String id) {
        return queryById(databaseHelper,id);
    }
    @Override
    public Student queryById(DatabaseHelper databaseHelper, String id) {
        String sql = "select * from " + StudentDao.tableName + " where " + StudentDao.id + "=?";
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{id});
        //若查询到结果
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) { //后面条件满足一条即可
            Student student = cursorToPojo(cursor);
            cursor.close();
            db.close();
            return student;
        }
        //如果查询到的结果为空
        return null;
    }

    @Override
    public List<Student> queryAll(DatabaseHelper databaseHelper) {
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        List<Student> list=new ArrayList<>();
//        Cursor cursor = db.rawQuery("select * from person where name like ? and age=?", new String[]{"%传智%", "4"});
//        Cursor cursor = db.rawQuery(“select * from person”, null);
        Cursor cursor=db.query(
                StudentDao.tableName, null, null, null,
                null, null, null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            list.add(cursorToPojo(cursor));
        }
        cursor.close();
        db.close();
        return list;
    }

    @Override
    public int update(DatabaseHelper databaseHelper, ContentValues contentValues) {
        return 0;
    }

    @Override
    public int update(ContentValues contentValues) {
        return 0;
    }

    private Student cursorToPojo(Cursor cursor){
        Student student=new Student();
        student.setId(cursor.getString(cursor.getColumnIndex(StudentDao.id)));
        student.setName(cursor.getString(cursor.getColumnIndex(StudentDao.name)));
        student.setSex(cursor.getString(cursor.getColumnIndex(StudentDao.sex)));
        student.setPhone(cursor.getString(cursor.getColumnIndex(StudentDao.phone)));
        student.setEmail(cursor.getString(cursor.getColumnIndex(StudentDao.email)));
        return student;
    }
}
