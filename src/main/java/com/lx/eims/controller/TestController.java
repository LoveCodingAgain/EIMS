package com.lx.eims.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * author: lixing
 * date: 2019-02-12
 * time: 21:42
 * description:测试控制器
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "测试热部署是否!";
    }
}
