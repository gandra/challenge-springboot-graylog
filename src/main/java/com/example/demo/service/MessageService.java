package com.example.demo.service;

import com.example.demo.repository.MessageRepository;
import com.example.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private Logger logger = LoggerFactory.getLogger (getClass());

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findAll() {
        List<Message> found = messageRepository.findAll();
        logger.info("Find all found " + found.size() + " messages.");
        return found;
    }

    public Message saveOrUpdate(Message message) {
        logger.info("Save " + message);
        return messageRepository.save(message);
    }

    public void deleteById(String id) {
        logger.info("Delete " + id);
        messageRepository.deleteById(id);
    }
}
