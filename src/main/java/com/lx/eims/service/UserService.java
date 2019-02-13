package com.lx.eims.service;
import com.lx.eims.entity.User;
/**
 * author: lixing
 * date: 2019-02-13
 * time: 17:17
 * description:用户查询接口
 */
public interface UserService {
    User getUser(Long userId);
}
