package com.example.blogapi.model;

public class Comment {
    private String id;
    private String author;
    private String postId;
    private String body;

    public Comment() {
    }

    public Comment(String id, String author, String postId, String body) {
        this.id = id;
        this.author = author;
        this.postId = postId;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
