package com.example.demo.controller;

import com.example.demo.service.MessageService;
import com.example.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MessageController {

    private Logger logger = LoggerFactory.getLogger (getClass());

    @Autowired
    private MessageService messageService;

    @GetMapping(value = "/messages")
    @ResponseBody
    public List<Message> getAllStudents() {
        return messageService.findAll();
    }

    @PostMapping(value = "/messages")
    @ResponseBody
    public ResponseEntity<?> saveOrUpdate(@RequestBody Message message) {
        messageService.saveOrUpdate(message);
        return new ResponseEntity("Message added successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/messages/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        messageService.deleteById(id);
        return new ResponseEntity("Message deleted successfully", HttpStatus.OK);
    }

}
