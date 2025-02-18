package com.simpleblog.hello_blog.service;

import com.simpleblog.hello_blog.model.PostInfo;
import com.simpleblog.hello_blog.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    public void createPost(PostInfo postInfo)
    {
        postRepo.save(postInfo);
    }

    public PostInfo updatePost(PostInfo postInfo)
    {
        PostInfo post = postRepo.findById(postInfo.getPostId()).orElseThrow();

        post.setTitle(postInfo.getTitle());
        post.setDescription(postInfo.getDescription());
        post.setImage(postInfo.getImage());
        post.setCreatedDate(postInfo.getCreatedDate());
        post.setActive(postInfo.isActive());
        post.setUser(postInfo.getUser());

        return postRepo.save(post);
    }
}
