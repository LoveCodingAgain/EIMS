package com.lx.eims.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.eims.entity.system.SysStaff;
import com.lx.eims.util.PageUtils;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-16
 * time: 15:17
 * description:系统员工业务层
 */
public interface SysStaffService extends IService<SysStaff> {

    PageUtils queryPage(Map<String, Object> params);
    /**
     * 查询用户的所有菜单ID
     * @param userId
     * @return
     */
    List<Integer> queryAllMenuId(Integer userId);


    /**
     * 保存员工信息
     * @param user
     */
    void saveUser(SysStaff user);

    /**
     * 更新员工信息
     * @param user
     */
    void update(SysStaff user);


    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    boolean updatePassword(Integer userId, String password, String newPassword);
}
