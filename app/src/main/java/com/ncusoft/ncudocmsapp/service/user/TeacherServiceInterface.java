package com.ncusoft.ncudocmsapp.service.user;

import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.Teacher;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;

import java.util.List;
import java.util.Map;

public interface TeacherServiceInterface {

    /**
     * 获取当前登录教师的所有课程
     * @param teacherId
     * @return
     */
    Map<Teacher,List<TeacherCourse>> getTeacherCourseByTeacherId(String teacherId);
}
