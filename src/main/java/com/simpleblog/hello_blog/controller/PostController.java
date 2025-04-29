package com.simpleblog.hello_blog.controller;

import com.simpleblog.hello_blog.model.PostInfo;
import com.simpleblog.hello_blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/api/posts")
    public List<PostInfo> getPost()
    {
        return postService.getAllPost();
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

