package com.simpleblog.hello_blog.dto;

public class UserRequest {

    String name;
    String password;
    boolean isEmailLogin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmailLogin() {
        return isEmailLogin;
    }

    public void setEmailLogin(boolean emailLogin) {
        isEmailLogin = emailLogin;
    }
}
