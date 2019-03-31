package com.lx.eims.controller;
import com.lx.eims.util.ShiroUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @RequestMapping("/login.html")
    public String login(){
        return "admin/login.html";
    }

    /**
     * 系统登录首页,必须是登录认证过的.
     * @return
     */
    @RequestMapping("/index.html")
    public String index(){
        return "admin/index.html";
    }

    /**
     * 系统注销
     * @return
     */
    @RequestMapping("/logout.html")
    public String logout(){
        // 销毁Session.
        ShiroUtils.logout();
        return "redirect:/login.html";
    }

    /**
     * 系统欢迎页面
     * @return
     */
    @RequestMapping("/main.html")
    public String main(){
        return "main.html";
    }

    /**
     * 系统404错误页面
     * @return
     */
    @RequestMapping("/404.html")
    public String error404(){
        return "404.html";
    }

    /**
     * 系统500错误页面
     * @return
     */
    @RequestMapping("/500.html")
    public String error500(){
        return "500.html";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }
}
