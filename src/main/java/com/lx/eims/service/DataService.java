package com.lx.eims.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.eims.entity.data.Data;
import com.lx.eims.util.PageUtils;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-04-09
 * time: 0:14
 * description:系统数据管理
 */
public interface DataService extends IService<Data> {
    /**
     * 查询分页
     * @param params
     * @return
     */
    PageUtils getDatas(Map<String, Object> params);

    /**
     * 全部数据
     * @return
     */
    List<Data> dataList();
}
