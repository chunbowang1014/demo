package com.example.demo.controller;

import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Resource
    UserService userService;

    /* this is method*/
    @GetMapping(value = "/sayHello")
    public String SayHello() {
        return userService.getUserInfo();
    }

    @GetMapping(value = "/updateUser")
    public String updateUser() {
        return userService.updateUser();
    }

}
