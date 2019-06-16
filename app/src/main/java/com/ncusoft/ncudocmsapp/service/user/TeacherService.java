package com.ncusoft.ncudocmsapp.service.user;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.Teacher;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;
import com.ncusoft.ncudocmsapp.repository.course.CourseDao;
import com.ncusoft.ncudocmsapp.repository.course.TeacherCourseDao;
import com.ncusoft.ncudocmsapp.repository.teacher.TeacherDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherService implements TeacherServiceInterface{
    public static final String TAG="Teacher.TeacherService:";

    private TeacherDao teacherDao=TeacherDao.getInstance();
    private CourseDao courseDao=CourseDao.getInstance();
    private TeacherCourseDao teacherCourseDao=TeacherCourseDao.getInstance();

    @Override
    public Map<Teacher,List<TeacherCourse>> getTeacherCourseByTeacherId(String teacherId) {
        SQLiteDatabase db= ClientApplication.getDatabaseHelper().getWritableDatabase();
        //多表连接查询 查询教师选课表以及课程信息
        String sql="select * from "+CourseDao.tableName+
                " as c inner join "+TeacherCourseDao.tableName+
                " as tc on c."+CourseDao.id+" =tc."+
                TeacherCourseDao.courseId+" WHERE "+
                TeacherCourseDao.teacherId+"="+teacherId+";";

        Cursor cursor= db.rawQuery(sql,null);
        List<TeacherCourse> list=new ArrayList<>();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            Course course=new Course();
            course.setId(cursor.getString(cursor.getColumnIndex(TeacherCourseDao.courseId)));
            course.setName(cursor.getString(cursor.getColumnIndex(CourseDao.name)));
            TeacherCourse teacherCourse=new TeacherCourse();
            teacherCourse.setTerm(cursor.getString(cursor.getColumnIndex(TeacherCourseDao.term)));
            teacherCourse.setClassCount(cursor.getString(cursor.getColumnIndex(TeacherCourseDao.classCount)));
            teacherCourse.setCourse(course);
            list.add(teacherCourse);
        }
        cursor.close();
        db.close();
        Teacher teacher=teacherDao.queryById(teacherId);//查询教师
        Map<Teacher,List<TeacherCourse>> map= new HashMap<>();
        map.put(teacher,list);
        return map;
//        List<TeacherCourse> teacherCourseList=teacherCourseDao.queryByTeacherId(teacherId); //教师选课表
//        //根据教师选课信息，查出没门课的信息
//        for (int i=0;i<teacherCourseList.size();i++){
//            Course course=courseDao.queryById(teacherCourseList.get(i).getCourseId());
//            teacherCourseList.get(i).setCourse(course);
//        }
    }
}