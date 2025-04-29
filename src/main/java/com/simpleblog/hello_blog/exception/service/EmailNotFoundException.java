package com.simpleblog.hello_blog.exception.service;

public class EmailNotFoundException extends RuntimeException{

    public EmailNotFoundException(String message)
    {
        super(message);
    }
}
