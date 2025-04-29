package com.simpleblog.hello_blog.service;

import com.simpleblog.hello_blog.model.PostInfo;
import com.simpleblog.hello_blog.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepo postRepo;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public void createPost(PostInfo postInfo)
    {
        postRepo.save(postInfo);
    }

    public List<PostInfo> getAllPost()
    {
        return postRepo.findAll();
    }
    public PostInfo updatePost(PostInfo postInfo)
    {
        PostInfo post = postRepo.findById(postInfo.getPostId()).orElseThrow();

        post.setTitle(postInfo.getTitle());
        post.setDescription(postInfo.getDescription());
        post.setImage(postInfo.getImage());
        post.setCreatedDate(postInfo.getCreatedDate());
        post.setActive(postInfo.isActive());

        return postRepo.save(post);
    }

}
