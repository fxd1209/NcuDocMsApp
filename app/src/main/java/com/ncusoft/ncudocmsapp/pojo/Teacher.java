package com.ncusoft.ncudocmsapp.pojo;

import android.content.ContentValues;

import java.io.Serializable;

public class Teacher extends User implements Serializable {
    private String name;
    private String sex;
    private String phone;
    private String email;
    private String proTitle;

    public Teacher(){};
    public Teacher(TeacherBuilder teacherBuilder){
        super(teacherBuilder);
        this.name=teacherBuilder.name;
        this.sex=teacherBuilder.sex;
        this.phone=teacherBuilder.phone;
        this.email=teacherBuilder.email;
        this.proTitle=teacherBuilder.proTitle;
    };

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getProTitle() {
        return proTitle;
    }
    public void setProTitle(String proTitle) {
        this.proTitle = proTitle;
    }


    public static class TeacherBuilder extends UserBuilder{
        private String name;
        private String sex;
        private String phone;
        private String email;
        private String proTitle;

        public TeacherBuilder id(String id){
            super.id(id);
            return this;
        }
        public TeacherBuilder password(String password){
            super.password(password);
            return this;
        }
        public TeacherBuilder authority(String authority){
            super.authority(authority);
            return this;
        }

        public TeacherBuilder name(String name){
           this.name=name;
            return this;
        }
        public TeacherBuilder sex(String sex){
            this.sex=sex;
            return this;
        }
        public TeacherBuilder phone(String phone){
            this.phone=phone;
            return this;
        }
        public TeacherBuilder email(String email){
            this.email=email;
            return this;
        }
        public TeacherBuilder proTitle(String proTitle){
            this.proTitle=proTitle;
            return this;
        }
        public Teacher build(){
            return new Teacher(this);
        }

    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", proTitle='" + proTitle + '\'' +
                '}';
    }
    @Override
    public ContentValues toContentValues(){
        ContentValues contentValues=super.toContentValues();
        contentValues.put("name",this.name);
        contentValues.put("sex",this.sex);
        contentValues.put("phone",this.phone);
        contentValues.put("email",this.email);
        contentValues.put("proTitle",this.proTitle);
        return contentValues;
    }
}
