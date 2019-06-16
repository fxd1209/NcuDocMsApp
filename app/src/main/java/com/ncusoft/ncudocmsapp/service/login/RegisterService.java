package com.ncusoft.ncudocmsapp.service.login;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.pojo.Student;
import com.ncusoft.ncudocmsapp.pojo.Teacher;
import com.ncusoft.ncudocmsapp.pojo.User;
import com.ncusoft.ncudocmsapp.repository.login.UserDao;
import com.ncusoft.ncudocmsapp.repository.student.StudentDao;
import com.ncusoft.ncudocmsapp.repository.teacher.TeacherDao;
import com.ncusoft.ncudocmsapp.utils.VerifyUtil;

public class RegisterService implements RegisterServiceInterface {
    public static final String TAG="User.RegisterService:";

    @Override
    public String inputCheck(User user) {
        String id="",name="",pwd="";
        //获取到对象的实际类型并强转(向下转型)
        Class<?> userClass=user.getClass();
        if (userClass.getSimpleName().equals("Teacher")){
//            Teacher teacher=(Teacher)user;
            Teacher teacher= Teacher.class.cast(user);
           if (teacher!=null){
               id=teacher.getId();
               name=teacher.getName();
               pwd=teacher.getPassword();
           }
        }else if (userClass.getSimpleName().equals("Student")){
            Student student= Student.class.cast(user);
            if (student!=null){
                id=student.getId();
                name=student.getName();
                pwd=student.getPassword();
            }
        }
        //检测输入的合法性
        if(id.equals("") && name.equals("") && pwd.equals("")) return "请输入信息!";
        if (!VerifyUtil.isMobile(id)) return "请输入合法手机号!";
        if(!VerifyUtil.isZhName(name)) return "请输入合法中文名!";
        if (!VerifyUtil.isPassword(pwd))return "密码为6-20位字母和数字";
        return "SUCCESS";
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        SQLiteDatabase db= ClientApplication.getDatabaseHelper().getWritableDatabase();

        User user=new User.UserBuilder()
                .id(teacher.getId())
                .password(teacher.getPassword())
                .authority(teacher.getAuthority())
                .build();
        //更新user_table和teacher_table
        ContentValues cvUser=user.toContentValues();
        ContentValues cvTeacher=teacher.toContentValues();
        //teacher_table里没有这字段，加载ContentValues会报错
        cvTeacher.remove("password");
        cvTeacher.remove("authority");
        //事务中不是同一个连接不能用事务，如开始一个事务，调用不同Dao里面的insert就错误，
        // 因为dao的连接和事务不是同一个连接，这就意味着事务要单独写
        //            db.intert不会引发异常,可以根据返回值自定义异常
        //            db.insertOrThrow和db.execSQL();才会引发异常
        //开启事务
        db.beginTransaction();
        try {
            //userDao.insert(cvUser);此句错误
            db.insertOrThrow(UserDao.tableName,null,cvUser);
            db.insertOrThrow(TeacherDao.tableName,null,cvTeacher);
            db.setTransactionSuccessful();
            return true;
        }catch (android.database.sqlite.SQLiteConstraintException e ){
            e.printStackTrace();
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.endTransaction();
            db.close();
        }
    }

    @Override
    public boolean addStudent(Student student) {
        SQLiteDatabase db= ClientApplication.getDatabaseHelper().getWritableDatabase();
        User user=new User.UserBuilder()
                .id(student.getId())
                .password(student.getPassword())
                .authority(student.getAuthority())
                .build();
        //更新user_table和teacher_table
        ContentValues cvUser=user.toContentValues();
        ContentValues cvStudent=student.toContentValues();
        //teacher_table里没有这字段，加载ContentValues会报错
        cvStudent.remove("password");
        cvStudent.remove("authority");
        //事务中不是同一个连接不能用事务，如开始一个事务，调用不同Dao里面的insert就错误，
        // 因为dao的连接和事务不是同一个连接，这就意味着事务要单独写
        //            db.intert不会引发异常,可以根据返回值自定义异常
        //            db.insertOrThrow和db.execSQL();才会引发异常
        //开启事务
        db.beginTransaction();
        try {
            //userDao.insert(cvUser);此句错误
            db.insertOrThrow(UserDao.tableName,null,cvUser);
            db.insertOrThrow(StudentDao.tableName,null,cvStudent);
            db.setTransactionSuccessful();
            return true;
        }catch (android.database.sqlite.SQLiteConstraintException e ){
            e.printStackTrace();
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.endTransaction();
            db.close();
        }
    }
}
