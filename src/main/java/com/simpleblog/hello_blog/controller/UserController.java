package com.simpleblog.hello_blog.controller;

import com.simpleblog.hello_blog.dto.LoginResponse;
import com.simpleblog.hello_blog.dto.UserRequest;
import com.simpleblog.hello_blog.dto.UserResponse;
import com.simpleblog.hello_blog.mapper.UserMapper;
import com.simpleblog.hello_blog.model.User;
import com.simpleblog.hello_blog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String greet()
    {
        return "Welcome";
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest request)
    {
        User user = UserMapper.toEntity(request);
        userService.registerUser(user);

        UserResponse response = UserMapper.toResponse(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest request)
    {
        User user = UserMapper.toEntity(request);
        String token = userService.verify(user, request.isEmailLogin());

        LoginResponse response= new LoginResponse();
        response.setMessage("Login successful");
        response.setToken(token);

        return ResponseEntity.ok(response);
     }


}
