package com.ncusoft.ncudocmsapp.service.login;

import android.util.Log;

import com.ncusoft.ncudocmsapp.pojo.User;
import com.ncusoft.ncudocmsapp.repository.login.UserDao;
import com.ncusoft.ncudocmsapp.utils.SharedVar;

public class LoginService implements LoginInterface{
   private UserDao userDao= UserDao.getInstance();
    @Override
    public User login(String id, String password) {
        User user=userDao.queryById(SharedVar.getClientApplication().getDatabaseHelper(),id);
        if (user!=null && user.getId().equals(id) && user.getPassword().equals(password)){
            return user;
        }else{
            return null;
        }
    }
}
