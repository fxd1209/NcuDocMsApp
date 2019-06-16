package com.ncusoft.ncudocmsapp.pojo;

import android.content.ContentValues;

import java.io.Serializable;

public class StudentCourse implements Pojo, Serializable {
    private String id;    //数据库自增 不用设置
    private String studentId;
    private String courseId;
    private String term;
    private String classCount;

    private Student student;
    private Course course;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    @Override
    public ContentValues toContentValues() {
        return null;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "id='" + id + '\'' +
                ", studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", term='" + term + '\'' +
                ", classCount='" + classCount + '\'' +
                ", student=" + student +
                ", course=" + course +
                '}';
    }

    public StudentCourse(){};
    public StudentCourse(StudentCourse.StudentCourseBuilder stuCourseBuilder ){
        this.id=stuCourseBuilder.id;
        this.studentId=stuCourseBuilder.studentId;
        this.courseId=stuCourseBuilder.courseId;
        this.term=stuCourseBuilder.term;
        this.classCount=stuCourseBuilder.classCount;
        this.student=stuCourseBuilder.student;
        this.course=stuCourseBuilder.course;
    }

    public static class StudentCourseBuilder{
        private String id;
        private String studentId;
        private String courseId;
        private String term;
        private String classCount;
        private Student student;
        private Course course;

        public StudentCourse.StudentCourseBuilder id(String id){
            this.id=id;
            return this;
        }
        public StudentCourse.StudentCourseBuilder studentId(String studentId){
            this.studentId=studentId;
            return this;
        }
        public StudentCourse.StudentCourseBuilder courseId(String courseId){
            this.courseId=courseId;
            return this;
        }
        public StudentCourse.StudentCourseBuilder term(String term){
            this.term=term;
            return this;
        }
        public StudentCourse.StudentCourseBuilder classCount(String classCount){
            this.classCount=classCount;
            return this;
        }
        public StudentCourse.StudentCourseBuilder student(Student student){
            this.student=student;
            return this;
        }
        public StudentCourse.StudentCourseBuilder course(Course course){
            this.course=course;
            return this;
        }
        public StudentCourse build(){
            return new StudentCourse(this);
        }
    }
}
