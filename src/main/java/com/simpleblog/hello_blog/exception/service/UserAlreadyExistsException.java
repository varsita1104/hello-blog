package com.simpleblog.hello_blog.exception.service;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String message)
    {
        super(message);
    }
}
