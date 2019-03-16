package com.lx.eims.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author: lixing
 * date: 2019-02-12
 * time: 21:42
 * description:页面控制器
 */
@Controller
public class PageController {
    /**
     * 跳转系统登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "admin/login";
    }
}
