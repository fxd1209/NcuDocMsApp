package com.ncusoft.ncudocmsapp.service.course;

import com.ncusoft.ncudocmsapp.pojo.Course;

import java.util.List;

public interface CourseServiceInterface {

    public List<Course> getAllCourseList();
    public long addCourse(Course course);
}
