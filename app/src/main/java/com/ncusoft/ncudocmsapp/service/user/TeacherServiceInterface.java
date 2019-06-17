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
     * 获得当前登陆者的详细信息
     * @return
     */
    public Teacher getUserInfo();
    /**
     * 根据ID 获得教师
     * @param teacherId
     * @return
     */
    public Teacher getTeacherById(String teacherId);
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

    /**
     * 教师选课
     */
    public boolean selectedCourse(List<TeacherCourse> teaCourseList);
    public boolean selectedCourse(String term,String classCount,Map<String,Course> courseMap);

    /**
     * 教师输入信息检测,姓名，电话，性别不能为空
     * @param teacher
     * @return
     */
    public String inputCheck(Teacher teacher);

    /**
     * 根据ID更新教师数据
     * @param teacher
     * @return
     */
    public long updateById(Teacher teacher);
}
