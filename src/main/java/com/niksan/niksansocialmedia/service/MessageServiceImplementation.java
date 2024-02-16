package com.niksan.niksansocialmedia.service;

import com.niksan.niksansocialmedia.models.Chat;
import com.niksan.niksansocialmedia.models.Message;
import com.niksan.niksansocialmedia.models.User;
import com.niksan.niksansocialmedia.repository.ChatRepository;
import com.niksan.niksansocialmedia.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImplementation  implements MessageService{

    private MessageRepository messageRepository;

    private ChatService chatService;

    private ChatRepository chatRepository;


    public MessageServiceImplementation(MessageRepository messageRepository, ChatService chatService, ChatRepository chatRepository) {
        this.messageRepository = messageRepository;
        this.chatService = chatService;
        this.chatRepository = chatRepository;
    }

    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {


        Chat chat = chatService.findChatById(chatId);
        Message message = new Message();

        message.setChat(chat);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());
        Message savedMessage = messageRepository.save(message);
        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);

        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {

//        Chat chat = chatService.findChatById(chatId);
        return messageRepository.findByChatId(chatId);
    }
}
