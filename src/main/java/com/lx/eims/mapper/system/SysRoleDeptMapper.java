package com.lx.eims.mapper.system;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.eims.entity.system.SysRoleDept;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 14:26
 * description:系统角色和部门
 */
@Repository
public interface SysRoleDeptMapper extends BaseMapper<SysRoleDept> {

    /**
     * 根据角色ID,获取部门ID列表
     * @param roleIds
     * @return
     */
    List<Integer> queryDeptIdList(Integer[] roleIds);

    /**
     * 根据角色ID数组,批量删除
     * @param roleIds
     * @return
     */
    int deleteBatch(Integer[] roleIds);
}
