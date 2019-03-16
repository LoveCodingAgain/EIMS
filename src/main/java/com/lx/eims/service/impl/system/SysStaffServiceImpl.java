package com.lx.eims.service.impl.system;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.eims.entity.system.SysStaff;
import com.lx.eims.mapper.system.SysStaffMapper;
import com.lx.eims.service.SysStaffService;
import com.lx.eims.util.PageUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-16
 * time: 15:24
 * description:员工业务层实现
 */
@Service
public class SysStaffServiceImpl extends ServiceImpl<SysStaffMapper, SysStaff> implements SysStaffService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<Integer> queryAllMenuId(Integer userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public void saveUser(SysStaff user) {

    }

    @Override
    public void update(SysStaff user) {

    }

    @Override
    public boolean updatePassword(Integer userId, String password, String newPassword) {
        return false;
    }
}
