package com.ncusoft.ncudocmsapp.service.user;

import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.Student;
import com.ncusoft.ncudocmsapp.pojo.StudentCourse;
import com.ncusoft.ncudocmsapp.pojo.Teacher;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TeacherServiceInterface {

    /**
     * 获取当前登录教师的所有课程
     * @param teacherId
     * @return
     */
    public Map<Teacher,List<TeacherCourse>> getTeaCourseByTeacherId(String teacherId);

    /**
     * 获得自己某个班的学生列表
     * @return
     */
    public Map<Course, ArrayList<StudentCourse>> getStudentList(TeacherCourse teacherCourse);
}
