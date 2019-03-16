package com.lx.eims.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.eims.entity.system.SysLog;
import com.lx.eims.util.PageUtils;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 17:06
 * description:系统日志查询分页接口
 */
public interface SysLogService extends IService<SysLog> {
    /**
     * 分页查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);
}
