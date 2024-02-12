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

    @GetMapping("/api/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("api/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception{
       return userService.findUserById(id);
    }

    @PutMapping("api/users")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody  User user) throws Exception{
        User reqUser = userService.findUserByJwt(jwt);
        return userService.updateUser(user, reqUser.getId());
    }

    @DeleteMapping("api/users/{userId}")
    public String deleteUser(@PathVariable Integer userId) throws Exception{
        User delete =
                userRepository.findById(userId).orElseThrow(() -> new Exception("User not exist with id " + userId));
        userRepository.delete(delete);
        return "User deleted successfully!! " + userId;
    }

    @PutMapping("api/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt , @PathVariable Integer userId2) throws  Exception{
        User reqUser = userService.findUserByJwt(jwt);
        return userService.followUser(reqUser.getId(), userId2);
    }

    @GetMapping("api/users/search")
    public List<User> searchUser(@RequestParam String query){
        return userService.searchUser(query);
    }

    @GetMapping("api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }
}
