package com.ncusoft.ncudocmsapp.pojo;

public class Course {
    //TODO:测试字段
    private String id;
    private String name;
    private String credit;//学分
    private String term;
    private String classCount;

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

}
