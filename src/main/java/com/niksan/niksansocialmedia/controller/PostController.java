package com.niksan.niksansocialmedia.controller;

import com.niksan.niksansocialmedia.models.Post;
import com.niksan.niksansocialmedia.response.ApiResponse;
import com.niksan.niksansocialmedia.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("posts/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) throws Exception {
        return new ResponseEntity<>(postService.createNewPost(post, userId), HttpStatus.CREATED) ;
    }

    @DeleteMapping("posts/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
        String message = postService.deletePost(postId, userId);
        ApiResponse res = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
    }

    @GetMapping("posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        return new ResponseEntity<Post>(postService.findPostById(postId), HttpStatus.ACCEPTED);
    }

    @GetMapping("posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId){
        return new ResponseEntity<List<Post>>(postService.findPostByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("posts")
    public ResponseEntity<List<Post>> findAllPost(){
        return new ResponseEntity<List<Post>>(postService.findAllPost(), HttpStatus.OK);
    }

    @PutMapping("posts/save/{postId}/user/{userId}")
    public ResponseEntity<Post> savePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
        return new ResponseEntity<Post>(postService.savedPost(postId, userId), HttpStatus.ACCEPTED);
    }

    @PutMapping("posts/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
        return new ResponseEntity<Post>(postService.likePost(postId, userId), HttpStatus.ACCEPTED);
    }

}
