package com.inspur.dynamicdatasource.datatest;

import com.inspur.dynamicdatasource.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testSelectAll() {
        userService.selectAll().forEach(System.out::println);
    }
}
