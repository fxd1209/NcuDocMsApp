package com.ncusoft.ncudocmsapp.service.login;

import com.ncusoft.ncudocmsapp.pojo.User;

public interface LoginInterface {

    /**
     * 登录查询
     * @param id
     * @param password
     * @return 密码账号正确，返回该用户 否则返回 null
     */
    public User login(String id, String password);
}
