package com.inspur.dynamicdatasource.service;

import com.inspur.dynamicdatasource.config.DynamicSwitchDataSource;
import com.inspur.dynamicdatasource.entity.User;
import com.inspur.dynamicdatasource.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @DynamicSwitchDataSource
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
