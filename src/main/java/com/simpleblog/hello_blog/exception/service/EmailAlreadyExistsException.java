package com.simpleblog.hello_blog.exception.service;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(String message)
    {
        super(message);
    }
}
