package com.simpleblog.hello_blog.controller;

import com.simpleblog.hello_blog.model.PostInfo;
import com.simpleblog.hello_blog.model.User;
import com.simpleblog.hello_blog.service.PostService;
import com.simpleblog.hello_blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.util.Date;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String greet()
    {
        return "Welcome";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user)
    {
        User registerUser = userService.registerUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully" + registerUser.toString());

    }

    @PostMapping("/login")
    public String login(@RequestBody User user)
    {
        String status = userService.verify(user);
        System.out.println(status);
        return status;
     }


}
