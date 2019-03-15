package com.lx.eims.mapper.system;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.eims.entity.system.SysDept;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 14:22
 * description:系统部门管理
 */
@Repository
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 集合查询
     * @param params
     * @return
     */
    List<SysDept> queryList(Map<String, Object> params);

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Long> queryDetpIdList(Integer parentId);

}
