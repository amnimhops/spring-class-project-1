package com.example.blogapi.errors;

public class ServiceException extends RuntimeException{
    public ServiceException(String error){
        super(error);
    }
}
