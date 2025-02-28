package com.simpleblog.hello_blog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;

@Entity
public class PostInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId")
    private int postId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private String title;

    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    private String description;
    private Date createdDate;
    private boolean isActive;

    public PostInfo()
    {

    }
    public PostInfo(int userId, String title, String description, Date date, byte[] image) {
        user = new User();
        user.setUserId(userId);
        this.title = title;
        this.description = description;
        this.isActive = true;
        this.createdDate = date;
        this.image = image;
    }

    @JsonProperty("userId")
    public int getUserId()
    {
        return user.getUserId();
    }
    public int getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "PostInfo{" +
                "username=" + user.getUserId()+
                ", postId=" + postId +
                ", title='" + title + '\'' +
                ", image=" + Arrays.toString(image) +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", isActive=" + isActive +
                '}';
    }
}
