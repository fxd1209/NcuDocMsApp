package com.ncusoft.ncudocmsapp.service.user;

import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.StudentCourse;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;
import com.ncusoft.ncudocmsapp.repository.course.StudentCourseDao;

import java.util.List;
import java.util.Map;

public class StudentService implements StudentServiceInterface{
    private StudentCourseDao studentCourseDao=StudentCourseDao.getInstance();
    @Override
    public boolean selectCourse(StudentCourse studentCourse) {
        if (studentCourse==null) return false;
        return !(-1==studentCourseDao.insert(studentCourse.toContentValues()));
    }

    @Override
    public boolean selectedCourse(List<StudentCourse> stuCourseList) {
        return false;
    }

    @Override
    public boolean selectedCourse(String term, String classCount, Map<String, Course> courseMap) {
        return false;
    }
}
