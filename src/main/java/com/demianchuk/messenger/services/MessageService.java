package com.demianchuk.messenger.services;

import com.demianchuk.messenger.database.DatabaseClass;
import com.demianchuk.messenger.models.Message;

import java.util.*;
import java.util.stream.Collectors;

public class MessageService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public List<Message> getAllMessagesForYear(int year) {
        Calendar calendar = Calendar.getInstance();
        List<Message> messagesForYear =
                messages.values()
                        .stream()
                        .filter(m -> {
                            calendar.setTime(m.getCreated());
                            return calendar.get(Calendar.YEAR) == year;
                        })
                        .collect(Collectors.toList());
        return messagesForYear;
    }

    public Message getMessage(long id) {
        return messages.get(id);
    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        message.setCreated(new Date());
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {
        if(message.getId() <= 0)
            return null;
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(long id) {
        return messages.remove(id);
    }
}