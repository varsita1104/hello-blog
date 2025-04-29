package com.simpleblog.hello_blog.dto;

public class UserResponse {

    public String username;
    public String emailId;
    public int userid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "username='" + username + '\'' +
                ", emailId='" + emailId + '\'' +
                ", userid=" + userid +
                '}';
    }
}
