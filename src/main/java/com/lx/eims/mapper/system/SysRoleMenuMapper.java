package com.lx.eims.mapper.system;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.eims.entity.system.SysRoleMenu;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 14:30
 * description:系统角色菜单
 */
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    /**
     *  根据角色ID,获取菜单ID列表
     * @param roleId
     * @return
     */
    List<Long> queryMenuIdList(Long roleId);

    /**
     * 根据角色ID数组，批量删除
     * @param roleIds
     * @return
     */
    int deleteBatch(Long[] roleIds);
}
