package com.lx.eims.controller.system;
import com.lx.eims.service.SysLogService;
import com.lx.eims.util.Message;
import com.lx.eims.util.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-19
 * time: 23:43
 * description:系统日志控制器
 */
@Controller
@RequestMapping("/sys")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;
    /**
     * 系统日志页面
     * @return
     */
    @RequestMapping("/log.html")
    public String log(){
        return "admin/log.html";
    }

    /**
     * 列表
     */
    @RequestMapping("/log/list")
    @ResponseBody
    public Message list(@RequestParam Map<String, Object> params){
        PageUtils page=sysLogService.queryPage(params);
        return Message.ok().put("page", page);
    }

}
