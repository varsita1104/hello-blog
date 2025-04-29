package com.simpleblog.hello_blog.controller;

import com.simpleblog.hello_blog.model.User;
import com.simpleblog.hello_blog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

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
