package com.lx.eims.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author: lixing
 * date: 2019-02-12
 * time: 21:42
 * description:测试控制器
 */
@RestController
@Api("EIMS项目的热部署配置")
@RequestMapping("/eims")
public class TestController {

    @ApiOperation(value = "测试项目热部署配置",notes = "实战EIMS项目热部署配置")
    @ApiImplicitParam(name="",value = "")
    @RequestMapping("/reload/test")
    public String test(){
        return "测试热部署是否成功!";
    }
}
