package com.niksan.niksansocialmedia.service;

import com.niksan.niksansocialmedia.models.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user);

    public User findUserById(Integer userId) throws Exception;

    public User findUserByEmail(String email);

    public User followUser(Integer firstUserId, Integer secondUserId) throws Exception;

    public User updateUser(User user, Integer id) throws Exception;

    public List<User> searchUser(String query);


}
