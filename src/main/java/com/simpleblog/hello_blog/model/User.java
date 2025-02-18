package com.simpleblog.hello_blog.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String username;
    private String password;
    private String emailId;

    @OneToMany(mappedBy = "user")
    private List<PostInfo> postInfos;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<PostInfo> getPostInfos() {
        return postInfos;
    }

    public void setPostInfos(List<PostInfo> postInfos) {
        this.postInfos = postInfos;
    }

    @Override
    public String toString() {
        return "User{" +
                "postInfos=" + postInfos +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", userId=" + userId +
                '}';
    }
}
