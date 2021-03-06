package com.lx.eims.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.eims.entity.system.SysLog;
import com.lx.eims.util.PageUtils;

import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 17:06
 * description:系统日志查询分页接口
 */
public interface SysLogService extends IService<SysLog> {
    /**
     * 查询所有的日志
     * @return
     */
    List<SysLog> logList();

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据用户操作查询
     * @param operation
     * @param params
     * @return
     */
    PageUtils queryByOperate(String operation, Map<String, Object> params);

    /**
     * 根据时间段查询
     * @param startDate
     * @param endDate
     * @param params
     * @return
     */
    PageUtils queryByDate(String startDate, String endDate, Map<String, Object> params);
}
