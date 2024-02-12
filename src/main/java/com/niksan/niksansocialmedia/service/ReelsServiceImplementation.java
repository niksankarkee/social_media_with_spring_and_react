package com.niksan.niksansocialmedia.service;

import com.niksan.niksansocialmedia.models.Reels;
import com.niksan.niksansocialmedia.models.User;
import com.niksan.niksansocialmedia.repository.ReelsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImplementation implements ReelsService{

    private ReelsRepository reelsRepository;

    private UserService userService;

    public ReelsServiceImplementation(ReelsRepository reelsRepository, UserService userService) {
        this.reelsRepository = reelsRepository;
        this.userService = userService;
    }

    @Override
    public Reels createReel(Reels reel, User user) {
        Reels createReel = new Reels();
        createReel.setTitle(reel.getTitle());
        createReel.setUser(user);
        createReel.setVideo(reel.getVideo());

        return reelsRepository.save(createReel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReel(Integer userId) throws Exception {
        userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }
}
