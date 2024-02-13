package com.niksan.niksansocialmedia.service;

import com.niksan.niksansocialmedia.models.Chat;
import com.niksan.niksansocialmedia.models.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ChatService {

    public Chat createChat(User reqUser, User targetedUser);

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat(Integer userId);
}
