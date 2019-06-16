package com.ncusoft.ncudocmsapp.pojo;

import android.content.ContentValues;

import java.io.Serializable;

public class User implements Pojo, Serializable {

    private String id;
    private String password;
    private String authority;

    public User(){};
    public User(UserBuilder userBuilder){
        this.id=userBuilder.id;
        this.password=userBuilder.password;
        this.authority=userBuilder.authority;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public static class UserBuilder{
        private String id;
        private String password;
        private String authority;

        public UserBuilder id(String id){
            this.id=id;
            return this;
        }
        public UserBuilder password(String password){
            this.password=password;
            return this;
        }
        public UserBuilder authority(String authority){
            this.authority=authority;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "user{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",this.id);
        contentValues.put("password",this.password);
        contentValues.put("authority",this.authority);
        return contentValues;
    }
}
