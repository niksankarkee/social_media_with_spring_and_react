package com.niksan.niksansocialmedia.service;

import com.niksan.niksansocialmedia.models.Chat;
import com.niksan.niksansocialmedia.models.User;
import com.niksan.niksansocialmedia.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImplementation implements ChatService{

    private ChatRepository chatRepository;

    private  UserService userService;

    public ChatServiceImplementation(ChatRepository chatRepository, UserService userService) {
        this.chatRepository = chatRepository;
        this.userService = userService;
    }

    @Override
    public Chat createChat(User reqUser, User targetedUser) {
        Chat isExist = chatRepository.findChatByUsersId(targetedUser, reqUser);

        if(isExist != null){
            return  isExist;
        }

        Chat chat = new Chat();
        chat.getUsers().add(targetedUser);
        chat.getUsers().add(reqUser);
        chat.setTimestamp(LocalDateTime.now());

        return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> chat = chatRepository.findById(chatId);
        if(chat.isEmpty()){
            throw new Exception("Chat not found with id: "+ chatId );
        }
        return chat.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {
        return chatRepository.findByUsersId(userId);
    }
}
