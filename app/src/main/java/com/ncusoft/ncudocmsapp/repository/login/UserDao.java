package com.ncusoft.ncudocmsapp.repository.login;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.pojo.User;
import com.ncusoft.ncudocmsapp.repository.DatabaseHelper;
import com.ncusoft.ncudocmsapp.repository.TableInterface;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements TableInterface{

    private static UserDao dao;
    private static DatabaseHelper databaseHelper;
    //表名以及字段
    public final static String tableName="user_table";
    public final static String id="id";
    public final static String password="password";
    public final static String authority="authority";


    private UserDao(){
        UserDao.databaseHelper= ClientApplication.getDatabaseHelper();
    }
    public static UserDao getInstance(){
        if (dao==null)
            dao=new UserDao();
        return dao;
    }
    @Override
    public void onCreateTable(SQLiteDatabase db) {
        String sql="Create table if not exists "+UserDao.tableName +"("+UserDao.id+" TEXT primary key,"
                +UserDao.password +" TEXT,"
                +UserDao.authority+" TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgradeTable(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * @param databaseHelper
     * @param values 插入的值
     * @return 返回新添记录的行号，与主键id无关 插入失败返回 -1
     */
    @Override
    public long insert(DatabaseHelper databaseHelper, ContentValues values) {
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        long id=db.insert(UserDao.tableName,null,values);
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

    /**
     * 通过主键查询
     * @param databaseHelper
     * @param id
     * @return
     */
    @Override
    public User queryById(DatabaseHelper databaseHelper,String id){
        String sql="select * from "+ UserDao.tableName+" where "+ UserDao.id+"=?";
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,new String[]{id});
        //若查询到结果
        if(cursor != null && cursor.moveToFirst() && cursor.getCount()>0){ //后面条件满足一条即可
            User user=cursorToPojo(cursor);
            cursor.close();
            db.close();
            return user;
        }
        //如果查询到的结果为空
        return null;
    }

    public List<User> queryAll(DatabaseHelper databaseHelper){
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        List<User> list=new ArrayList<>();
//        Cursor cursor = db.rawQuery("select * from person where name like ? and age=?", new String[]{"%传智%", "4"});
//        Cursor cursor = db.rawQuery(“select * from person”, null);
        Cursor cursor=db.query(
                UserDao.tableName, null, null, null,
                null, null, null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            list.add(cursorToPojo(cursor));
//            User user=new User();
//            user.setId(cursor.getString(cursor.getColumnIndex(UserReposHelper.id)));
//            user.setPassword(cursor.getString(cursor.getColumnIndex(UserReposHelper.password)));
//            user.setAuthority(cursor.getString(cursor.getColumnIndex(UserReposHelper.authority)));
//            userList.add(user);
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

    private User cursorToPojo(Cursor cursor){
            User user=new User();
            user.setId(cursor.getString(cursor.getColumnIndex(UserDao.id)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(UserDao.password)));
            user.setAuthority(cursor.getString(cursor.getColumnIndex(UserDao.authority)));
            return user;
    }
}
