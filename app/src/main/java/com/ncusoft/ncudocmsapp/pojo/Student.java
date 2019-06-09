package com.ncusoft.ncudocmsapp.pojo;

import android.content.ContentValues;

public class Student extends User {
    private String name;
    private String sex;
    private String grade;
    private String classId;
    private String tel;

    public Student(){};
    public Student(StudentBuilder studentBuilder){
        super(studentBuilder);
        this.name=studentBuilder.name;
        this.sex=studentBuilder.sex;
        this.grade=studentBuilder.grade;
        this.classId=studentBuilder.classId;
        this.tel=studentBuilder.tel;
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
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public static class StudentBuilder extends UserBuilder{
        private String name;
        private String sex;
        private String grade;
        private String classId;
        private String tel;

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
        public StudentBuilder tel(String tel){
            this.tel=tel;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }

    @Override
    public String toString() {
        return "student{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", grade='" + grade + '\'' +
                ", classId='" + classId + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
    @Override
    public ContentValues toContentValues(){
        return null;
    }
}
