package com.niksan.niksansocialmedia.repository;

import com.niksan.niksansocialmedia.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    @Query("SELECT p FROM Post p WHERE p.user.id=:userId")
    public List<Post> findPostByUserId(Integer userId);
}
