package com.niksan.niksansocialmedia.service;

import com.niksan.niksansocialmedia.models.Post;
import com.niksan.niksansocialmedia.models.User;
import com.niksan.niksansocialmedia.repository.PostRepository;
import com.niksan.niksansocialmedia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImplementation implements PostService{

    PostRepository postRepository;

    UserService userService;

    UserRepository userRepository;

    public PostServiceImplementation(PostRepository postRepository, UserService userService, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {
        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(userService.findUserById(userId));

        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post =
                postRepository.findById(postId).orElseThrow(() -> new Exception("User not exist with id " + userId));
        User user = userService.findUserById(userId);

        if(post.getUser().getId().equals(user.getId())  ){
            postRepository.delete(post);
            return "Post deleted Successfully!";
        }
        throw new Exception("You can not delete others post");
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception{
        return postRepository.findById(postId).orElseThrow(() -> new Exception("Post not exist with id " + postId));
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception{
        Post post =
                postRepository.findById(postId).orElseThrow(() -> new Exception("User not exist with id " + userId));
        User user = userService.findUserById(userId);

        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }else {
            user.getSavedPost().add(post);
        }
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception{
        Post post =
                postRepository.findById(postId).orElseThrow(() -> new Exception("User not exist with id " + userId));
        User user = userService.findUserById(userId);

        if(post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }else {
            post.getLiked().add(user);
        }
        return postRepository.save(post);
    }
}
