package com.example.blogapi.entities;

public class User {
    private String id;
    private String email;
    private String realName;
    private String realSurname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealSurname() {
        return realSurname;
    }

    public void setRealSurname(String realSurname) {
        this.realSurname = realSurname;
    }
}
