package com.inspur.dynamicdatasource.service;

import com.inspur.dynamicdatasource.entity.User;
import com.inspur.dynamicdatasource.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(User user) throws Exception {
        int flag = userMapper.insert(user);
       // int e=4/0;
        return flag;
    }
}
