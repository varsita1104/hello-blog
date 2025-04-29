package com.simpleblog.hello_blog.mapper;

import com.simpleblog.hello_blog.dto.UserRequest;
import com.simpleblog.hello_blog.dto.UserResponse;
import com.simpleblog.hello_blog.model.User;

public class UserMapper {

    public static User toEntity(UserRequest request)
    {
        User user = new User();
        if(request.isEmailLogin())
        {
            user.setEmailId(request.getName());
        }
        else
        {
            user.setUsername(request.getName());
        }
        user.setPassword(request.getPassword());
        return user;
    }

    public static UserResponse toResponse(User user)
    {
        UserResponse response = new UserResponse();
        response.setUserid(user.getUserId());
        response.setUsername(user.getUsername());
        response.setEmailId(user.getEmailId());
        return response;
    }
}
