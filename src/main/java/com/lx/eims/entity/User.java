package com.lx.eims.entity;
import lombok.Getter;
import lombok.Setter;
/**
 * @author: lixing
 * date: 2019-02-13
 * time: 17:12
 * description:用户实体
 */
@Getter
@Setter
public class User {
    private Long userId;
    private String userName;
    private String userPassword;
}
