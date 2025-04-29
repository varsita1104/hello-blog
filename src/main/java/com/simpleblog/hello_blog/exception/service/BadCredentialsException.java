package com.simpleblog.hello_blog.exception.service;

public class BadCredentialsException extends RuntimeException{

    public BadCredentialsException(String message) {
        super(message);
    }
}
