package com.example.demo.controller;

import com.example.demo.service.MessageService;
import com.example.dto.Message;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Message Controller", description = "REST APIs related to Message Entity.")
@Controller
public class MessageController {

    private Logger logger = LoggerFactory.getLogger (getClass());

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    @ResponseBody
    public List<Message> getAll() {
        return messageService.findAll();
    }

    @PostMapping("/messages")
    @ResponseBody
    public ResponseEntity<?> saveOrUpdate(@RequestBody Message message) {
        messageService.saveOrUpdate(message);
        return new ResponseEntity("Message added successfully", HttpStatus.OK);
    }

    @DeleteMapping("/messages/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        messageService.deleteById(id);
        return new ResponseEntity("Message deleted successfully", HttpStatus.OK);
    }

}
