package com.ncusoft.ncudocmsapp.service.course;

import android.content.ContentValues;
import android.graphics.Point;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.activitys.teacher.AdminAddCourseActivity;
import com.ncusoft.ncudocmsapp.pojo.Course;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;
import com.ncusoft.ncudocmsapp.repository.course.CourseDao;
import com.ncusoft.ncudocmsapp.repository.course.TeacherCourseDao;
import com.ncusoft.ncudocmsapp.utils.ToastUtil;
import com.ncusoft.ncudocmsapp.utils.VerifyUtil;

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

    @Override
    public String inputCheck(Course course) {
        if (course==null) return "课程为空";
        if (VerifyUtil.isStrEmpty(course.getId()) ||
            VerifyUtil.isStrEmpty(course.getName()) ||
            VerifyUtil.isStrEmpty(course.getCredit()))
            return "课程号，课程名，学分不能为空";
       if (!VerifyUtil.isConsistsOfNum_Letter(course.getId()) || course.getId().length()<6)
            return "课程号为6位以上ASICC码";
       if(!VerifyUtil.isNum(course.getCredit()) || Float.parseFloat(course.getCredit())<0){
            return "学分不合法!";
        }
        return "SUCCESS";
    }

    @Override
    public int updateById(Course course) {
        if (course==null) return 0;
        return courseDao.update(course.toContentValues());
    }
}
