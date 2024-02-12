package com.niksan.niksansocialmedia.controller;

import com.niksan.niksansocialmedia.models.Comment;
import com.niksan.niksansocialmedia.models.User;
import com.niksan.niksansocialmedia.service.CommentService;
import com.niksan.niksansocialmedia.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private CommentService commentService;

    private UserService  userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer postId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        return commentService.createComment(comment, postId, user.getId());

    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment( @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer commentId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        return commentService.likeComment(commentId, user.getId());

    }
}
