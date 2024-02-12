package com.niksan.niksansocialmedia.controller;

import com.niksan.niksansocialmedia.models.Post;
import com.niksan.niksansocialmedia.models.User;
import com.niksan.niksansocialmedia.response.ApiResponse;
import com.niksan.niksansocialmedia.service.PostService;
import com.niksan.niksansocialmedia.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private PostService postService;

    private UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("posts")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt, @RequestBody Post post) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        return new ResponseEntity<>(postService.createNewPost(post, reqUser.getId()), HttpStatus.CREATED) ;
    }

    @DeleteMapping("posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String jwt,
                                                  @PathVariable Integer postId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        String message = postService.deletePost(postId, reqUser.getId());
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

    @PutMapping("posts/save/{postId}")
    public ResponseEntity<Post> savePostHandler(@RequestHeader("Authorization") String jwt,
                                                @PathVariable Integer postId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        return new ResponseEntity<Post>(postService.savedPost(postId, reqUser.getId()), HttpStatus.ACCEPTED);
    }

    @PutMapping("posts/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@RequestHeader("Authorization") String jwt,
                                                @PathVariable Integer postId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        return new ResponseEntity<Post>(postService.likePost(postId, reqUser.getId()), HttpStatus.ACCEPTED);
    }

}
