package com.lx.eims.mapper.system;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.eims.entity.system.SysStaffRole;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 14:18
 * description:员工角色关系
 */
@Repository
public interface SysStaffRoleMapper extends BaseMapper<SysStaffRole> {

    /**
     * 根据用户ID，获取角色ID列表
     * @param userId
     * @return
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据角色ID数组，批量删除
     * @param roleIds
     * @return
     */
    int deleteBatch(Long[] roleIds);
}
