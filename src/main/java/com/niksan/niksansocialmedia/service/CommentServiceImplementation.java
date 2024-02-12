package com.niksan.niksansocialmedia.service;

import com.niksan.niksansocialmedia.models.Comment;
import com.niksan.niksansocialmedia.models.Post;
import com.niksan.niksansocialmedia.models.User;
import com.niksan.niksansocialmedia.repository.CommentRepository;
import com.niksan.niksansocialmedia.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService{

    private  PostService postService;

    private UserService userService;

    private CommentRepository commentRepository;

    private PostRepository postRepository;

    public CommentServiceImplementation(PostService postService, UserService userService, CommentRepository commentRepository, PostRepository postRepository) {
        this.postService = postService;
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        post.getComments().add(savedComment);

        postRepository.save(post);
        return savedComment;

    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if(comment.isEmpty()){
            throw new Exception("Comment not exist");
        }
        return comment.get();
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {
        Comment comment = findCommentById(commentId);
        User user = userService.findUserById(userId);

        if(!comment.getLiked().contains(user)){
            comment.getLiked().add(user);

        }else{
            comment.getLiked().remove(user);
        }
        return commentRepository.save(comment);
    }
}
