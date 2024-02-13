package com.niksan.niksansocialmedia.service;

import com.niksan.niksansocialmedia.models.Story;
import com.niksan.niksansocialmedia.models.User;

import java.util.List;

public interface StoryService {

    public Story createStory(Story story, User user);

    public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
