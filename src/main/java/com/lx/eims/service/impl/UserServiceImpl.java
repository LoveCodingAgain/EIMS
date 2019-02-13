package com.lx.eims.service.impl;
import com.lx.eims.entity.User;
import com.lx.eims.mapper.UserMapping;
import com.lx.eims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * author: lixing
 * date: 2019-02-13
 * time: 17:21
 * description:用户查询接口实现
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapping userMapping;

    @Override
    public User getUser(Long userId) {
        return userMapping.findUserById(userId);
    }
}
