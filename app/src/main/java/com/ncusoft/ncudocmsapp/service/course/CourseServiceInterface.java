package com.ncusoft.ncudocmsapp.service.course;

import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;

import java.util.List;

public interface CourseServiceInterface {

    public List<Course> getAllCourseList();
    public long addCourse(Course course);

    public int deleteTeacherCourse(TeacherCourse tc);

    /**
     * 课程输入信息检测
     * @param course
     * @return
     */
    public String inputCheck(Course course);

    /**
     * 根据课程号更新课程
     * @return
     */
    public int updateById(Course course);
}
