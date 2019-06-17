package com.ncusoft.ncudocmsapp.service.course;

import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;

import java.util.List;

public interface CourseServiceInterface {

    public List<Course> getAllCourseList();
    public long addCourse(Course course);

    public int deleteTeacherCourse(TeacherCourse tc);
}
