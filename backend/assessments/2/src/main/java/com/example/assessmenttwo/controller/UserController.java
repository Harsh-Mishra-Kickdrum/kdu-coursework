package com.example.assessmenttwo.controller;

import com.example.assessmenttwo.entity.User;
import com.example.assessmenttwo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A controller to access the users
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * To get all the users list
     * @return
     */
    @GetMapping("/getAllUser")
    public List<User> get(){
        return userService.getAllUser();
    }

}
