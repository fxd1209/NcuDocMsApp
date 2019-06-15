package com.ncusoft.ncudocmsapp.pojo;

import android.content.ContentValues;

public class TeacherCourse implements Pojo{
    private String id;    //数据库自增 不用设置
    private String teacherId;
    private String courseId;
    private String term;
    private String classCount;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getClassCount() {
        return classCount;
    }

    public void setClassCount(String classCount) {
        this.classCount = classCount;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public TeacherCourse(){};
    public TeacherCourse(TeacherCourse.TeacherCourseBuilder teacherCourseBuilder ){
        this.id=teacherCourseBuilder.id;
        this.teacherId=teacherCourseBuilder.teacherId;
        this.courseId=teacherCourseBuilder.courseId;
        this.term=teacherCourseBuilder.term;
        this.classCount=teacherCourseBuilder.classCount;
    }

    public static class TeacherCourseBuilder{
        private String id;
        private String teacherId;
        private String courseId;
        private String term;
        private String classCount;

        public TeacherCourse.TeacherCourseBuilder id(String id){
            this.id=id;
            return this;
        }
        public TeacherCourse.TeacherCourseBuilder teacherId(String teacherId){
            this.teacherId=teacherId;
            return this;
        }
        public TeacherCourse.TeacherCourseBuilder courseId(String courseId){
            this.courseId=courseId;
            return this;
        }
        public TeacherCourse.TeacherCourseBuilder term(String term){
            this.term=term;
            return this;
        }
        public TeacherCourse.TeacherCourseBuilder classCount(String classCount){
            this.classCount=classCount;
            return this;
        }
        public TeacherCourse build(){
            return new TeacherCourse(this);
        }
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",this.id);
        contentValues.put("teacherId",this.teacherId);
        contentValues.put("courseId",this.courseId);
        contentValues.put("term",this.term);
        contentValues.put("classCount",this.classCount);
        return contentValues;
    }
}
