package com.lx.eims.service.impl.system;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.eims.entity.system.SysMenu;
import com.lx.eims.mapper.system.SysMenuMapper;
import com.lx.eims.service.SysMenuService;
import com.lx.eims.service.SysStaffService;
import com.lx.eims.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
/**
 * @author: lixing
 * date: 2019-03-18
 * time: 19:38
 * description:系统菜单业务层实现
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysStaffService sysStaffService;
    /**
     *
     * @param parentId 父菜单ID
     * @param menuIdList  子菜单列表
     * @return
     */
    @Override
    public List<SysMenu> queryListParentId(Integer parentId, List<Integer> menuIdList) {
        List<SysMenu> menuList=queryListParentId(parentId);
        // 如果用户菜单ID为空.
        if(menuIdList==null){
            return menuList;
        }
        List<SysMenu> userMenuList=new ArrayList<>();
        for(SysMenu menu:menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenu> queryListParentId(Integer parentId) {
        return this.baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<SysMenu> queryNotButtonList() {
        return this.baseMapper.queryNotButtonList();
    }

    @Override
    public List<SysMenu> getUserMenuList(Integer staffId) {
          // 如果是系统管理员拥有所有权限
        if(staffId == Constant.SUPER_ADMIN){
            // 获取所有的菜单
            return getAllMenuList(null);
        }
        //用户菜单列表
        List<Integer> menuIdList = sysStaffService.queryAllMenuId(staffId);
        return getAllMenuList(menuIdList);
    }

    /**null
     * 获取所有菜单列表
     * @param menuIdList
     */
    private List<SysMenu> getAllMenuList(List<Integer> menuIdList) {
         // 查询根菜单列表
        List<SysMenu> menuList = queryListParentId(0, menuIdList);
        System.out.println(menuList);
        // 查询子菜单
        getMenuTreeList(menuList, menuIdList);
       return menuList;
    }

    /**
     *
     * @param menuList 菜单列表
     * @param menuIdList 用户菜单ID列表
     * @return
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Integer> menuIdList) {
        // 存放子菜单
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();
        for(SysMenu entity : menuList){
            //目录
            if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        System.out.println(subMenuList);
        return subMenuList;
    }

    @Override
    public void delete(Integer menuId) {

    }


}
