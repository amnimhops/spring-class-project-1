package com.example.blogapi.entities;

import java.util.Date;

public class Post {
    private String id;

    private String authorEmail;
    private String title;
    private String body;
    private Date creationDate;
    private Date modificationDate;

    public Post(){

    }
    public Post(String id, String authorEmail, String title, String body, Date creationDate, Date modificationDate) {
        this.id = id;
        this.authorEmail = authorEmail;
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
