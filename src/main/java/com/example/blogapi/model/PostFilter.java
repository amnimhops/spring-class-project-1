package com.example.blogapi.model;

public class PostFilter {
    private String author;
    private String containsText;

    public PostFilter() {
    }

    public PostFilter(String author, String containsText) {
        this.author = author;
        this.containsText = containsText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContainsText() {
        return containsText;
    }

    public void setContainsText(String containsText) {
        this.containsText = containsText;
    }

    public boolean matches(Post post) {
        return post.getAuthorEmail().equals(author) || post.getTitle().contains(containsText);
    }
}
