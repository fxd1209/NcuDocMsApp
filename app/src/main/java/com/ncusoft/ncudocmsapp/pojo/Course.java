package com.ncusoft.ncudocmsapp.pojo;

import android.content.ContentValues;

public class Course implements Pojo{
    private String id;    //编号
    private String name;  //课程名
    private String credit;//学分

    public Course(){};
    public Course(Course.CourseBuilder courseBuilder){
        this.id=courseBuilder.id;
        this.name=courseBuilder.name;
        this.credit=courseBuilder.credit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public static class CourseBuilder{
        private String id;
        private String name;
        private String credit;

        public Course.CourseBuilder id(String id){
            this.id=id;
            return this;
        }
        public Course.CourseBuilder name(String name){
            this.name=name;
            return this;
        }
        public Course.CourseBuilder credit(String credit){
            this.credit=credit;
            return this;
        }
        public Course build(){
            return new Course(this);
        }
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",this.id);
        contentValues.put("name",this.name);
        contentValues.put("credit",this.credit);
        return contentValues;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", credit='" + credit + '\'' +
                '}';
    }
}
