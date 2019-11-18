package com.inspur.dynamicdatasource.controller;

import com.inspur.dynamicdatasource.config.annotation.DynamicSwitchDataSource;
import com.inspur.dynamicdatasource.entity.User;
import com.inspur.dynamicdatasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getAll() {
        return userService.selectAll();
    }

    @PostMapping("/user")
    public int insertOne(User user) {
        try {
            return userService.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
