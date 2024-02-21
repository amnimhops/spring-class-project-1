package com.example.blogapi.model;

public class ResourceDeletionStatus {
    private String resource;
    private String id;
    private String success;

    public ResourceDeletionStatus() {
    }

    public ResourceDeletionStatus(String resource, String id, String success) {
        this.resource = resource;
        this.id = id;
        this.success = success;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
