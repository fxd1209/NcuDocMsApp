package com.ncusoft.ncudocmsapp.pojo;

public class User {

    private String id;
    private String password;
    private String authority;

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

    @Override
    public String toString() {
        return "user{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
