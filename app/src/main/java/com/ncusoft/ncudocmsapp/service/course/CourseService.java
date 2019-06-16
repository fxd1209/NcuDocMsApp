package com.ncusoft.ncudocmsapp.service.course;

import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.repository.course.CourseDao;

import java.util.List;

public class CourseService implements CourseServiceInterface{
    private CourseDao courseDao=CourseDao.getInstance();
    @Override
    public List<Course> getAllCourseList() {
        return courseDao.queryAll();
    }
}
