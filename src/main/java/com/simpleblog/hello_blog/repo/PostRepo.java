package com.simpleblog.hello_blog.repo;

import com.simpleblog.hello_blog.model.PostInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<PostInfo, Integer> {

    List<PostInfo> findAllById(int userId);
}
