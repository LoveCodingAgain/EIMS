package com.lx.eims.controller.system;
import com.lx.eims.entity.system.SysMenu;
import com.lx.eims.service.SysMenuService;
import com.lx.eims.util.Message;
import com.lx.eims.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
/**
 * @author: lixing
 * date: 2019-03-18
 * time: 19:43
 * description:系统菜单管理控制器
 */
@Controller
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 导航菜单
     * @return
     */
    @RequestMapping("/nav")
    @ResponseBody
    public Message nav(){
        List<SysMenu> menuList=sysMenuService.getUserMenuList(ShiroUtils.getStaffId());
        return Message.ok().put("menuList", menuList);
    }

    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sys:menu:list")
    public List<SysMenu> list(){
        List<SysMenu> menuList = sysMenuService.list();
        for(SysMenu sysMenuEntity : menuList){
            SysMenu parentMenuEntity = sysMenuService.getById(sysMenuEntity.getParentId());
            if(parentMenuEntity != null){
                sysMenuEntity.setParentName(parentMenuEntity.getName());
            }
        }
        return menuList;
    }
}
