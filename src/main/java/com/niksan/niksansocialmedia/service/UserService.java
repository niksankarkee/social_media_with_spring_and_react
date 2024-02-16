package com.niksan.niksansocialmedia.service;

import com.niksan.niksansocialmedia.exceptions.UserException;
import com.niksan.niksansocialmedia.models.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user);

    public User findUserById(Integer userId) throws UserException;

    public User findUserByEmail(String email);

    public User followUser(Integer firstUserId, Integer secondUserId) throws UserException;

    public User updateUser(User user, Integer id) throws UserException;

    public List<User> searchUser(String query);

    public User findUserByJwt(String jwt);


}
