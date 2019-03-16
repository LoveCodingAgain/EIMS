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
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 17:13
 * description:系统日志业务层实现
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<SysLog> page = this.page(
                new QueryUtils<SysLog>().getPage(params),
                new QueryWrapper<SysLog>().like(StringUtils.isNotBlank(key),"username", key)
        );
        return new PageUtils(page);
    }
}
