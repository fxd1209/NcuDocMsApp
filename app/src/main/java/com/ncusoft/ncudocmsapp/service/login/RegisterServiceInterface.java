package com.ncusoft.ncudocmsapp.service.login;

import com.ncusoft.ncudocmsapp.pojo.Student;
import com.ncusoft.ncudocmsapp.pojo.Teacher;
import com.ncusoft.ncudocmsapp.pojo.User;

public interface RegisterServiceInterface {

    /**
     * 检测注册是输入合法性
     * @param user
     * @return
     */
    public String inputCheck(User user);

    /**
     * 注册教师
     * @return
     */
    public boolean addTeacher(Teacher teacher);

    /**
     * 注册学生
     * @return
     */
    public boolean addStudent(Student student);
}
