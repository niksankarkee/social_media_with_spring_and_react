package com.niksan.niksansocialmedia.repository;

import com.niksan.niksansocialmedia.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
