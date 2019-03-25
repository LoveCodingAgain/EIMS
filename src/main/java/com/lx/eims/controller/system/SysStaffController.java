package com.lx.eims.controller.system;
import com.lx.eims.annotation.RecordSysLog;
import com.lx.eims.service.SysStaffService;
import com.lx.eims.util.Md5HashUtils;
import com.lx.eims.util.Message;
import com.lx.eims.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-18
 * time: 21:49
 * description:测试
 */
@RestController
@RequestMapping("/sys/user")
public class SysStaffController {

    @Autowired
    private SysStaffService sysStaffService;
    /**
     * 获取登录的员工信息
     */
    @RequestMapping("/info")
    public Message info(){
        return Message.ok().put("user", ShiroUtils.getStaff());
    }

    /**
     * 修改员工密码
     * @param map
     * @return
     * 实体类接收/Map接收/HttpServletRequest中读取.
     */
    @RequestMapping(value="/password",method = RequestMethod.POST,consumes = "application/json")
    @RecordSysLog("员工修改密码")
    public Message password(@RequestBody Map map){
        String password=(String)map.get("password");
        String newPassword=(String)map.get("newPassword");
        System.out.println(password);
        System.out.println(newPassword);
        // 旧盐值加密密码
        String formMd5Password=Md5HashUtils.md5(password,ShiroUtils.getStaff().getSalt());
        // 新加密密码
        String dbMd5Password=Md5HashUtils.md5(newPassword,ShiroUtils.getStaff().getSalt());
        // 更新密码
        boolean flag=sysStaffService.updatePassword(ShiroUtils.getStaffId(),formMd5Password, dbMd5Password);
        // 返回修改结果提示信息
        if(!flag){
            return Message.error("旧的密码不正确,请再检查试试嘛!");
        }
        return Message.ok();
    }
}
