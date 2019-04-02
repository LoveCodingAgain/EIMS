package com.lx.eims.controller.project;
import com.lx.eims.entity.project.Project;
import com.lx.eims.service.ProjectService;
import com.lx.eims.util.Message;
import com.lx.eims.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author: lixing
 * date: 2019-04-01
 * time: 18:41
 * description:项目控制器
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    /**
     * 查询页面路由
      * @return
     */
    @RequestMapping("/search.html")
    public String search(){
        return "project/search.html";
    }

    /**
     * 新增页面路由
     * @return
     */
    @RequestMapping("/add.html")
    public String add(){
        return "project/add.html";
    }

    /**
     * 更新页面路由
     * @return
     */
    @RequestMapping("/update.html")
    public String update(){
        return "project/update.html";
    }

    /**
     * 项目负责人查询
     * @param params
     * @return
     */
    @RequestMapping(value="/search/principal")
    @ResponseBody
    public Message principal(@Valid @RequestParam Map<String, Object> params){
        PageUtils principalPage=projectService.getPrincipal((String)params.get("principal"), params);
        return Message.ok().put("page", principalPage);
    }

    /**
     * 全部项目
     * @param params
     * @return
     */
    @RequestMapping(value = "/search/all")
    @ResponseBody
    public Message all(@RequestParam Map<String, Object> params){
        PageUtils projectPage=projectService.getProjects(params);
        return Message.ok().put("page", projectPage);
    }

    /**
     * 已完成项目查询
     * @param params
     * @return
     */
    @RequestMapping(value="/search/finished")
    @ResponseBody
    public Message finished(@RequestParam Map<String, Object> params){
        PageUtils projectPage=projectService.getFinished(params);
        return Message.ok().put("page", projectPage);
    }

    /**
     * 未完成项目查询
     * @param params
     * @return
     */
    @RequestMapping(value="/search/unfinished")
    @ResponseBody
    public Message unFinished(@RequestParam Map<String, Object> params){
        PageUtils projectPage=projectService.getUnfinished(params);
        return Message.ok().put("page", projectPage);
    }

}
