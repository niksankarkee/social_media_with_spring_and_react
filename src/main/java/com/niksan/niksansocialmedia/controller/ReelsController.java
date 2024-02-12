package com.niksan.niksansocialmedia.controller;

import com.niksan.niksansocialmedia.models.Reels;
import com.niksan.niksansocialmedia.models.User;
import com.niksan.niksansocialmedia.service.ReelsService;
import com.niksan.niksansocialmedia.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReelsController {

    private ReelsService reelsService;

    private UserService userService;

    public ReelsController(ReelsService reelsService, UserService userService) {
        this.reelsService = reelsService;
        this.userService = userService;
    }

    @PostMapping("reels")
    public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt){
        User reqUser = userService.findUserByJwt(jwt);
        return reelsService.createReel(reel, reqUser);
    }

    @GetMapping("reels")
    public List<Reels> findAllReels(){
        return reelsService.findAllReels();
    }

    @GetMapping("reels/user/{userId}")
    public List<Reels> findUserReels(@PathVariable Integer userId) throws Exception {
        return reelsService.findUsersReel(userId);
    }

}
