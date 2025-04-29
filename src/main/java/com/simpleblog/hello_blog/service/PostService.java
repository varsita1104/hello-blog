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

    public List<PostInfo> getPostById(int userId)
    {
        return postRepo.findAllById(userId);
    }

    public PostInfo updatePost(PostInfo updatePost)
    {
        PostInfo post = postRepo.findById(updatePost.getPostId()).orElseThrow();

        boolean update = false;

        if(updatePost.getTitle() != null && updatePost.getTitle().equals(post.getTitle()))
        {
            update = true;
            post.setTitle(updatePost.getTitle());
        }
        if(updatePost.getDescription() != null && updatePost.getDescription().equals(post.getDescription()))
        {
            update = true;
            post.setDescription(updatePost.getDescription());
        }
        if(updatePost.getImage() != null && updatePost.getImage().equals(post.getImage()))
        {
            update = true;
            post.setImage(updatePost.getImage());
        }
        if(updatePost.getCreatedDate() != null && updatePost.getCreatedDate().equals(post.getCreatedDate()))
        {
            update = true;
            post.setCreatedDate(updatePost.getCreatedDate());
        }
        if(updatePost.isActive() != post.isActive())
        {
            update = true;
            post.setActive(updatePost.isActive());
        }
        if(update)
            return postRepo.save(post);
        return post;
    }

}
