package com.niksan.niksansocialmedia.service;

import com.niksan.niksansocialmedia.models.Reels;
import com.niksan.niksansocialmedia.models.User;

import java.util.List;

public interface ReelsService {

    public Reels createReel(Reels reel, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReel(Integer userId) throws Exception;
}
