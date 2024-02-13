package com.niksan.niksansocialmedia.controller;

import com.niksan.niksansocialmedia.models.Story;
import com.niksan.niksansocialmedia.models.User;
import com.niksan.niksansocialmedia.service.StoryService;
import com.niksan.niksansocialmedia.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class StoryController {

    private StoryService storyService;

    private UserService userService;

    public StoryController(StoryService storyService, UserService userService) {
        this.storyService = storyService;
        this.userService = userService;
    }

    @PostMapping("story")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt){
        User reqUser = userService.findUserByJwt(jwt);
        return storyService.createStory(story, reqUser);
    }

    @GetMapping("story/user/{userId}")
    public List<Story> findUserStory(@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {
//        User reqUser = userService.findUserByJwt(jwt);
        return  storyService.findStoryByUserId(userId);
    }
}
