package com.example.demo.controller;


import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value = "HelloWorld Controller", description = "REST APIs endpoint /hello.")
@Controller
public class HelloWorldController {

    private Logger logger = LoggerFactory.getLogger (getClass());

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        logger.info("Hello INFO World!");
        logger.debug("Hello DEBUG World!");
        return "Hello World!";
    }

}