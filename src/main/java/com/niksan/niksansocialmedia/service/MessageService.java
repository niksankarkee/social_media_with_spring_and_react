package com.niksan.niksansocialmedia.service;

import com.niksan.niksansocialmedia.models.Message;
import com.niksan.niksansocialmedia.models.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message req) throws Exception;

    public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
