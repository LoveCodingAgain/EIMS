package com.lx.eims.mapper.system;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.eims.entity.system.SysMenu;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 14:24
 * description:系统菜单
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据父菜单,查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(Integer parentId);

    /**
     * 获取不包含按钮的菜单列表
     * @return
     */
    List<SysMenu> queryNotButtonList();

}
