package com.ncusoft.ncudocmsapp.service.login;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.pojo.User;
import com.ncusoft.ncudocmsapp.repository.login.UserDao;

public class LoginService implements LoginServiceInterface {
   private UserDao userDao= UserDao.getInstance();
    @Override
    public User login(String id, String password) {
        User user=userDao.queryById(ClientApplication.getDatabaseHelper(),id);
        if (user!=null && user.getId().equals(id) && user.getPassword().equals(password)){
            return user;
        }
        return null;

    }
}
