package com.niksan.niksansocialmedia.controller;

import com.niksan.niksansocialmedia.models.User;
import com.niksan.niksansocialmedia.repository.UserRepository;
import com.niksan.niksansocialmedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception{
       return userService.findUserById(id);
    }

    @PostMapping("users")
    public User createUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PutMapping("users/{userId}")
    public User updateUser(@RequestBody  User user, @PathVariable Integer  userId) throws Exception{
        return userService.updateUser(user, userId);
    }

    @DeleteMapping("users/{userId}")
    public String deleteUser(@PathVariable Integer userId) throws Exception{
        User delete =
                userRepository.findById(userId).orElseThrow(() -> new Exception("User not exist with id " + userId));
        userRepository.delete(delete);
        return "User deleted successfully!! " + userId;
    }

    @PutMapping("users/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws  Exception{
        return userService.followUser(userId1, userId2);
    }

    @GetMapping("users/search")
    public List<User> searchUser(@RequestParam String query){
        return userService.searchUser(query);
    }
}
