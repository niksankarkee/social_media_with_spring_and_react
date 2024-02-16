package com.niksan.niksansocialmedia.service;

import com.niksan.niksansocialmedia.config.JwtProvider;
import com.niksan.niksansocialmedia.exceptions.UserException;
import com.niksan.niksansocialmedia.models.User;
import com.niksan.niksansocialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public User registerUser(User user) {
        User newUser=new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());

        return userRepository.save(newUser);
    }

    @Override
    public User findUserById(Integer userId) throws UserException{
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("User not exist with userid: " + userId);
    }

    @Override
    public User findUserByEmail(String email) {
       return userRepository.findByEmail(email);
    }

    @Override
    public User followUser(Integer reqUserId, Integer secondUserId) throws UserException {
        User reqUser = findUserById(reqUserId);
        User secondUser = findUserById(secondUserId);

        secondUser.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(secondUser.getId());

        userRepository.save(reqUser);
        userRepository.save(secondUser);

        return reqUser;
    }

    @Override
    public User updateUser(User user, Integer userId) throws UserException {

        User updateUser =
                userRepository.findById(userId).orElseThrow(() -> new UserException("User not exist with id " + userId));

        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setPassword(user.getPassword());
        updateUser.setGender(user.getGender());
        updateUser.setEmail(user.getEmail());

        return userRepository.save(updateUser);
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);

       return userRepository.findByEmail(email);

    }
}
