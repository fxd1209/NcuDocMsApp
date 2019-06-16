package com.ncusoft.ncudocmsapp.repository.course;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.pojo.StudentCourse;
import com.ncusoft.ncudocmsapp.repository.DatabaseHelper;
import com.ncusoft.ncudocmsapp.repository.TableInterface;

import java.util.ArrayList;
import java.util.List;

public class StudentCourseDao implements TableInterface {
    private static StudentCourseDao dao;
    private static DatabaseHelper databaseHelper;
    //表名以及字段
    public final static String tableName="student_course_table";
    public final static String id="id";
    public final static String studentId="studentId";
    public final static String courseId="courseId";
    public final static String term="term";
    public final static String classCount="classCount";


    private StudentCourseDao(){
        StudentCourseDao.databaseHelper= ClientApplication.getDatabaseHelper();
    }
    public static StudentCourseDao getInstance(){
        if (dao==null)
            dao=new StudentCourseDao();
        return dao;
    }
    @Override
    public void onCreateTable(SQLiteDatabase db) {
        //自增字段，插入是设为null
        String sql="Create table if not exists "+StudentCourseDao.tableName +"("+StudentCourseDao.id+" integer primary key autoincrement,"
                +StudentCourseDao.studentId +" TEXT,"
                +StudentCourseDao.courseId +" TEXT,"
                +StudentCourseDao.term +" TEXT,"
                +StudentCourseDao.classCount+" TEXT);";
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
        long id=db.insert(StudentCourseDao.tableName,null,values);
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
    public StudentCourse queryById(DatabaseHelper databaseHelper, String id){
        String sql="select * from "+ StudentCourseDao.tableName+" where "+ StudentCourseDao.id+"=?";
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,new String[]{id});
        //若查询到结果
        if(cursor != null && cursor.moveToFirst() && cursor.getCount()>0){ //后面条件满足一条即可
            StudentCourse studentCourse=cursorToPojo(cursor);
            cursor.close();
            db.close();
            return studentCourse;
        }
        //如果查询到的结果为空
        return null;
    }
    public List<StudentCourse> queryByStudentId(String id){
        return queryByStudentId(databaseHelper,id);
    }
    public List<StudentCourse> queryByStudentId(DatabaseHelper databaseHelper, String id){
        String sql="select * from "+ StudentCourseDao.tableName+" where "+ StudentCourseDao.studentId+"=?";
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,new String[]{id});
        //若查询到结果
        List<StudentCourse> list=new ArrayList<>();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            list.add(cursorToPojo(cursor));
        }
        cursor.close();
        db.close();
        return list;
    }
    public List<StudentCourse> queryAll(DatabaseHelper databaseHelper){
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        List<StudentCourse> list=new ArrayList<>();
//        Cursor cursor = db.rawQuery("select * from person where name like ? and age=?", new String[]{"%传智%", "4"});
//        Cursor cursor = db.rawQuery(“select * from person”, null);
        Cursor cursor=db.query(
                StudentCourseDao.tableName, null, null, null,
                null, null, null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            list.add(cursorToPojo(cursor));
        }
        cursor.close();
        db.close();
        return list;
    }

    private StudentCourse cursorToPojo(Cursor cursor){
        return new StudentCourse.StudentCourseBuilder()
                .id(cursor.getString(cursor.getColumnIndex(StudentCourseDao.id)))
                .studentId(cursor.getString(cursor.getColumnIndex(StudentCourseDao.studentId)))
                .courseId(cursor.getString(cursor.getColumnIndex(StudentCourseDao.courseId)))
                .term(cursor.getString(cursor.getColumnIndex(StudentCourseDao.term)))
                .classCount(cursor.getString(cursor.getColumnIndex(StudentCourseDao.classCount)))
                .build();
    }
}
