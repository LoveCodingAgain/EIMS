package com.lx.eims.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.eims.entity.contract.Constract;
import com.lx.eims.util.PageUtils;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-04-09
 * time: 20:50
 * description:合同业务层
 */
public interface ConstractService extends IService<Constract> {
    /**
     * 查询全部合同
     * @param params
     * @return
     */
    PageUtils getConstracts(Map<String, Object> params);

    /**
     * 更新合同
     * @param constract
     * @return
     */
    int updateConstract(Constract constract);

    /**
     * 全部合同
     */
    List<Constract> dataList();
}
