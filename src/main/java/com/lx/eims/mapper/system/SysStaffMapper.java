package com.lx.eims.mapper.system;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.eims.entity.system.SysStaff;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 14:15
 * description:系统用户
 */
@Repository
public interface SysStaffMapper extends BaseMapper<SysStaff> {
    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPermas(Integer userId);

    /**
     * 查询用户的所有菜单ID
     * @param userId
     * @return
     */
    List<Integer> queryAllMenuId(Integer userId);
}
