package com.niksan.niksansocialmedia.controller;

import com.niksan.niksansocialmedia.models.Chat;
import com.niksan.niksansocialmedia.models.User;
import com.niksan.niksansocialmedia.request.CreateChatRequest;
import com.niksan.niksansocialmedia.service.ChatService;
import com.niksan.niksansocialmedia.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ChatController {
    private ChatService chatService;

    private UserService userService;

    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @PostMapping("chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt, @RequestBody CreateChatRequest req) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        User targetUser = userService.findUserById(req.getUserId());
        return chatService.createChat(reqUser, targetUser);

    }

    @GetMapping("chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        return chatService.findUsersChat(user.getId());

    }
}
