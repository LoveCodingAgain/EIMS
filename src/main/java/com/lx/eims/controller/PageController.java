package com.lx.eims.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * author: lixing
 * date: 2019-03-05
 * time: 13:07
 * description:
 */
@Controller
public class PageController {
    @RequestMapping("/demo")
    public String demo(){
        return "demo";
    }
}
