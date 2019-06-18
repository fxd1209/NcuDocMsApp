package com.ncusoft.ncudocmsapp.service.user;

import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.StudentCourse;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;

import java.util.List;
import java.util.Map;

public interface StudentServiceInterface {

    /**
     * 学生选课
     */
    public boolean selectCourse(StudentCourse studentCourse);
    public boolean selectedCourse(List<StudentCourse> stuCourseList);
    public boolean selectedCourse(String term, String classCount, Map<String, Course> courseMap);
}
