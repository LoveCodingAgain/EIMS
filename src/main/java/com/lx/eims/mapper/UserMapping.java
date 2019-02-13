package com.lx.eims.mapper;
import com.lx.eims.entity.User;
import org.springframework.stereotype.Repository;
/**
 * author: lixing
 * date: 2019-02-13
 * time: 17:15
 * description:用户Mapper
 */
@Repository
public interface UserMapping {
    /**根据用户ID查询用户信息*/
    User findUserById(Long userId);
}
