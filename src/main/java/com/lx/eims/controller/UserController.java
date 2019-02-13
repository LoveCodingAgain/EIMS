package com.lx.eims.controller;
import com.lx.eims.entity.User;
import com.lx.eims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * author: lixing
 * date: 2019-02-13
 * time: 17:23
 * description:用户查询控制器
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/eims/user/{userId}")
    public User getUser(@PathVariable("userId") Long userId){
        return userService.getUser(userId);
    }
}
