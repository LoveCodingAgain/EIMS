package com.lx.eims.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.eims.entity.system.SysMenu;
import java.util.List;
/**
 * @author: lixing
 * date: 2019-03-18
 * time: 19:24
 * description:系统菜单管理业务层
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    List<SysMenu> queryListParentId(Integer parentId, List<Integer> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(Integer parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<SysMenu> getUserMenuList(Integer userId);

    /**
     * 删除
     */
    void delete(Integer menuId);
}
