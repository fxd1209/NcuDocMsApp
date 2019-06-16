package com.ncusoft.ncudocmsapp.pojo;

import android.content.ContentValues;

public class Student extends User {
    private String name;
    private String sex;
    private String grade;
    private String classId;
    private String phone;
    private String email;

    public Student(){};
    public Student(StudentBuilder studentBuilder){
        super(studentBuilder);
        this.name=studentBuilder.name;
        this.sex=studentBuilder.sex;
        this.grade=studentBuilder.grade;
        this.classId=studentBuilder.classId;
        this.phone=studentBuilder.phone;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getClassId() {
        return classId;
    }
    public void setClassId(String classId) {
        this.classId = classId;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public static class StudentBuilder extends UserBuilder{
        private String name;
        private String sex;
        private String grade;
        private String classId;
        private String phone;
        private String email;

        public StudentBuilder id(String id){
            super.id(id);
            return this;
        }
        public StudentBuilder password(String password){
            super.password(password);
            return this;
        }
        public StudentBuilder authority(String authority){
            super.authority(authority);
            return this;
        }

        public StudentBuilder name(String name){
            this.name=name;
            return this;
        }

        public StudentBuilder sex(String sex){
            this.sex=sex;
            return this;
        }

        public StudentBuilder grade(String grade){
            this.grade=grade;
            return this;
        }
        public StudentBuilder classId(String classId){
            this.classId=classId;
            return this;
        }
        public StudentBuilder phone(String phone){
            this.phone=phone;
            return this;
        }
        public StudentBuilder email(String email){
            this.email=email;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", grade='" + grade + '\'' +
                ", classId='" + classId + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public ContentValues toContentValues(){
        return null;
    }
}
