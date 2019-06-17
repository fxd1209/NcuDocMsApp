package com.ncusoft.ncudocmsapp.repository.course;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;
import com.ncusoft.ncudocmsapp.repository.DatabaseHelper;
import com.ncusoft.ncudocmsapp.repository.TableInterface;

import java.util.ArrayList;
import java.util.List;

public class TeacherCourseDao implements TableInterface {

    private static TeacherCourseDao dao;
    private static DatabaseHelper databaseHelper;
    //表名以及字段
    public final static String tableName="teacher_course_table";
    public final static String id="id";
    public final static String teacherId="teacherId";
    public final static String courseId="courseId";
    public final static String term="term";
    public final static String classCount="classCount";


    private TeacherCourseDao(){
        TeacherCourseDao.databaseHelper= ClientApplication.getDatabaseHelper();
    }
    public static TeacherCourseDao getInstance(){
        if (dao==null)
            dao=new TeacherCourseDao();
        return dao;
    }
    @Override
    public void onCreateTable(SQLiteDatabase db) {
        //自增字段，插入是设为null
        String sql="Create table if not exists "+TeacherCourseDao.tableName +"("+TeacherCourseDao.id+" integer primary key autoincrement,"
                +TeacherCourseDao.teacherId +" TEXT,"
                +TeacherCourseDao.courseId +" TEXT,"
                +TeacherCourseDao.term +" TEXT,"
                +TeacherCourseDao.classCount+" TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgradeTable(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     *
     * @param databaseHelper
     * @param values 插入的值
     * @return 返回新添记录的行号，与主键id无关 插入失败返回 -1
     */
    @Override
    public long insert(DatabaseHelper databaseHelper, ContentValues values) {
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        long id=db.insert(TeacherCourseDao.tableName,null,values);
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

    /**
     * 通过主键查询
     * @param databaseHelper
     * @param id
     * @return
     */
    @Override
    public TeacherCourse queryById(DatabaseHelper databaseHelper, String id){
        String sql="select * from "+ TeacherCourseDao.tableName+" where "+ TeacherCourseDao.id+"=?";
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,new String[]{id});
        //若查询到结果
        if(cursor != null && cursor.moveToFirst() && cursor.getCount()>0){ //后面条件满足一条即可
            TeacherCourse teacherCourse=cursorToPojo(cursor);
            cursor.close();
            db.close();
            return teacherCourse;
        }
        //如果查询到的结果为空
        return null;
    }
    public List<TeacherCourse> queryByTeacherId(String id){
        return queryByTeacherId(databaseHelper,id);
    }
    public List<TeacherCourse> queryByTeacherId(DatabaseHelper databaseHelper, String id){
        String sql="select * from "+ TeacherCourseDao.tableName+" where "+ TeacherCourseDao.teacherId+"=?";
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,new String[]{id});
        //若查询到结果
        List<TeacherCourse> list=new ArrayList<>();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            list.add(cursorToPojo(cursor));
        }
        cursor.close();
        db.close();
        return list;
    }
    public List<TeacherCourse> queryAll(DatabaseHelper databaseHelper){
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        List<TeacherCourse> list=new ArrayList<>();
//        Cursor cursor = db.rawQuery("select * from person where name like ? and age=?", new String[]{"%传智%", "4"});
//        Cursor cursor = db.rawQuery(“select * from person”, null);
        Cursor cursor=db.query(
                TeacherCourseDao.tableName, null, null, null,
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

    private TeacherCourse cursorToPojo(Cursor cursor){
        return new TeacherCourse.TeacherCourseBuilder()
                .id(cursor.getString(cursor.getColumnIndex(TeacherCourseDao.id)))
                .teacherId(cursor.getString(cursor.getColumnIndex(TeacherCourseDao.teacherId)))
                .courseId(cursor.getString(cursor.getColumnIndex(TeacherCourseDao.courseId)))
                .term(cursor.getString(cursor.getColumnIndex(TeacherCourseDao.term)))
                .classCount(cursor.getString(cursor.getColumnIndex(TeacherCourseDao.classCount)))
                .build();
    }
}
