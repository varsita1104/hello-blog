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
    @Autowired
    private PostService postService;

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

    @PostMapping("/create/post")
    public ResponseEntity<String> savePost(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("userId") int userId,
            @RequestParam("creationDate") String creationDateStr,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) throws ParseException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); // Use your format
        Date creationDate = sdf.parse(creationDateStr);
        byte[] imgFile = null;
        if(imageFile != null && !imageFile.isEmpty())
        {
            imgFile = imageFile.getBytes();
        }
        PostInfo post = new PostInfo(userId, title, description, creationDate, imgFile);

        postService.createPost(post);

        return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully" + post.toString());
    }
}
