package com.ncusoft.ncudocmsapp.service.course;

import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;
import com.ncusoft.ncudocmsapp.repository.course.CourseDao;
import com.ncusoft.ncudocmsapp.repository.course.TeacherCourseDao;

import java.util.List;

public class CourseService implements CourseServiceInterface{
    private CourseDao courseDao=CourseDao.getInstance();
    private TeacherCourseDao teacherCourseDao=TeacherCourseDao.getInstance();
    @Override
    public List<Course> getAllCourseList() {
        return courseDao.queryAll();
    }

    @Override
    public long addCourse(Course course) {
        return courseDao.insert(course.toContentValues());
    }

    @Override
    public int deleteTeacherCourse(TeacherCourse tc) {
        if (tc==null) return 0;
        return teacherCourseDao.deleteByTidCidClassCount(
                tc.getTeacherId(),tc.getCourseId(),tc.getClassCount());

    }
}
