package com.ncusoft.ncudocmsapp.service.user;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.Student;
import com.ncusoft.ncudocmsapp.pojo.StudentCourse;
import com.ncusoft.ncudocmsapp.pojo.Teacher;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;
import com.ncusoft.ncudocmsapp.pojo.User;
import com.ncusoft.ncudocmsapp.repository.course.CourseDao;
import com.ncusoft.ncudocmsapp.repository.course.StudentCourseDao;
import com.ncusoft.ncudocmsapp.repository.course.TeacherCourseDao;
import com.ncusoft.ncudocmsapp.repository.student.StudentDao;
import com.ncusoft.ncudocmsapp.repository.teacher.TeacherDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherService implements TeacherServiceInterface{
    public static final String TAG="Teacher.TeaService:";

    private TeacherDao teacherDao=TeacherDao.getInstance();
    private CourseDao courseDao=CourseDao.getInstance();
    private TeacherCourseDao teacherCourseDao=TeacherCourseDao.getInstance();

    @Override
    public Teacher getUserInfo() {
       return getTeacherById(
               ClientApplication.getInstance().getCurrentLoginUser().getId());
    }

    @Override
    public Teacher getTeacherById(String teacherId) {
        return teacherDao.queryById(teacherId);
    }

    @Override
    public Map<Teacher,List<TeacherCourse>> getTeaCourseByTeacherId(String teacherId) {
        SQLiteDatabase db= ClientApplication.getDatabaseHelper().getReadableDatabase();
        //多表连接查询 查询教师选课表以及课程信息
        String sql="select * from "+CourseDao.tableName+
                " as c inner join "+TeacherCourseDao.tableName+
                " as tc on c."+CourseDao.id+" =tc."+
                TeacherCourseDao.courseId+" WHERE "+
                TeacherCourseDao.teacherId+"="+teacherId+";";
        Log.i(TAG+"tea:",sql);
        Cursor cursor= db.rawQuery(sql,null);
        List<TeacherCourse> list=new ArrayList<>();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            Course course=new Course.CourseBuilder()
                    .id(cursor.getString(cursor.getColumnIndex(TeacherCourseDao.courseId)))
                    .name(cursor.getString(cursor.getColumnIndex(CourseDao.name)))
                    .credit(cursor.getString(cursor.getColumnIndex(CourseDao.credit)))
                    .build();

            TeacherCourse teacherCourse=new TeacherCourse.TeacherCourseBuilder()
                    .courseId(cursor.getString(cursor.getColumnIndex(TeacherCourseDao.courseId)))
                    .course(course)
                    .teacherId(cursor.getString(cursor.getColumnIndex(TeacherCourseDao.teacherId)))
                    .classCount(cursor.getString(cursor.getColumnIndex(TeacherCourseDao.classCount)))
                    .term(cursor.getString(cursor.getColumnIndex(TeacherCourseDao.term)))
                    .build();
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

    @Override
    public Map<Course,ArrayList<StudentCourse>> getStudentList(TeacherCourse teacherCourse) {
        SQLiteDatabase db= ClientApplication.getDatabaseHelper().getReadableDatabase();
        String courseId=teacherCourse.getCourseId();
        String term=teacherCourse.getTerm();
        String classCount=teacherCourse.getClassCount();  //这三个确定一个班级
        //多表连接查询 查询教师选课表以及课程信息
        String sql="select * from "+ StudentDao.tableName+
                " as s inner join "+ StudentCourseDao.tableName+
                " as sc on s."+StudentDao.id+" =sc."+
                StudentCourseDao.studentId+" WHERE sc."+
                StudentCourseDao.courseId+"='"+courseId+"' and "+
                StudentCourseDao.term+"='"+term+"' and "+
                StudentCourseDao.classCount+"='"+classCount+"';";
        Log.i(TAG+"stu:",sql);
        Cursor cursor= db.rawQuery(sql,null);
        ArrayList<StudentCourse> list=new ArrayList<>();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            Student student=new Student();
            student.setId(cursor.getString(cursor.getColumnIndex(StudentCourseDao.studentId)));
            student.setName(cursor.getString(cursor.getColumnIndex(StudentDao.name)));
            student.setSex(cursor.getString(cursor.getColumnIndex(StudentDao.sex)));
            StudentCourse studentCourse=new StudentCourse();
            studentCourse.setTerm(cursor.getString(cursor.getColumnIndex(StudentCourseDao.term)));
            studentCourse.setClassCount(cursor.getString(cursor.getColumnIndex(StudentCourseDao.classCount)));
            studentCourse.setStudent(student);
            list.add(studentCourse);
        }
        cursor.close();
        db.close();
        Course course=courseDao.queryById(courseId);
        Map<Course,ArrayList<StudentCourse>> map= new HashMap<>();
        map.put(course,list);
        return map;
    }

    @Override
    public boolean selectedCourse(List<TeacherCourse> teaCourseList) {
        boolean insertAll=true;
        for (TeacherCourse teacherCourse:teaCourseList){
            if (-1==teacherCourseDao.insert(teacherCourse.toContentValues()))
                insertAll=false;
        }
        return insertAll;
    }

    @Override
    public boolean selectedCourse(String term,String classCount,Map<String, Course> courseMap) {
        User user=ClientApplication.getInstance().getCurrentLoginUser();
        if (user==null) try {
            throw new Exception("未登录，当前用户为空异常!");
        } catch (Exception e) {
            e.printStackTrace();
        }else{
            if (courseMap.size()<=0) return false;
            List<TeacherCourse> teaCourseList=new ArrayList<>();
            for (Map.Entry<String,Course> entry:courseMap.entrySet()){
                teaCourseList.add(
                        new TeacherCourse.TeacherCourseBuilder()
                        .id(null)
                        .teacherId(user.getId())
                        .courseId(entry.getValue().getId())
                        .term(term)
                        .classCount(classCount)
                        .build());
            }
            return selectedCourse(teaCourseList);

        }
        return false;
    }


}
