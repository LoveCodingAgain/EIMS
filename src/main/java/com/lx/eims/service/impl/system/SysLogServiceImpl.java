package com.lx.eims.service.impl.system;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.eims.entity.system.SysLog;
import com.lx.eims.mapper.system.SysLogMapper;
import com.lx.eims.service.SysLogService;
import com.lx.eims.util.PageUtils;
import com.lx.eims.util.QueryUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 17:13
 * description:系统日志业务层实现
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    /**
     * 查询全部日志
     * @return
     */
    @Override
    public List<SysLog> logList() {
        // 无查询条件,默认查询全部日志
        List<SysLog> list=this.baseMapper.selectList(null);
        return list;
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<SysLog> page = this.page(
                new QueryUtils<SysLog>().getPage(params),
                new QueryWrapper<SysLog>().like(StringUtils.isNotBlank(key),"username", key)
        );
        return new PageUtils(page);
    }

    /**
     * 根据用户操作查询,及分页
     * @param operation
     * @param params
     * @return
     */
    @Override
    public PageUtils queryByOperate(String operation, Map<String, Object> params) {
        IPage<SysLog> conditionPage = this.page(
                new QueryUtils<SysLog>().getPage(params),
                new QueryWrapper<SysLog>().like("operation", operation)
        );
        return new PageUtils(conditionPage);
    }

    /**
     * 根据时间段查询
     * @param startDate
     * @param endDate
     * @param params
     * @return
     */
    @Override
    public PageUtils queryByDate(String startDate, String endDate, Map<String, Object> params) {
        IPage<SysLog> datePage = this.page(
                new QueryUtils<SysLog>().getPage(params),
                new QueryWrapper<SysLog>().between("create_date",startDate,endDate)
        );
        return new PageUtils(datePage);
    }
}
