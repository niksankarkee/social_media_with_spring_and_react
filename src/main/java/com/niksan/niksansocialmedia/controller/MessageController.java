package com.niksan.niksansocialmedia.controller;

import com.niksan.niksansocialmedia.models.Message;
import com.niksan.niksansocialmedia.models.User;
import com.niksan.niksansocialmedia.service.MessageService;
import com.niksan.niksansocialmedia.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class MessageController {

    private MessageService messageService;

    private UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("message/chat/{chatId}")
    public Message createMessage(@RequestHeader("Authorization") String jwt, @RequestBody Message req,
                                 @PathVariable Integer chatId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        return messageService.createMessage(user, chatId, req);
    }

    @GetMapping("message/chat/{chatId}")
    public List<Message> findChatsMessage(@PathVariable Integer chatId) throws Exception {

//        User user = userService.findUserByJwt(jwt);

        return messageService.findChatsMessages(chatId);
    }

}
