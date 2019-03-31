package com.lx.eims.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.eims.entity.assets.AssetsInfo;
import com.lx.eims.util.PageUtils;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-29
 * time: 12:45
 * description:资产管理
 */
public interface AssetsService extends IService<AssetsInfo> {
    /**
     * 查询全部的资产
     * @return
     */
    List<AssetsInfo> assetsList();


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
    PageUtils queryByCategory(String operation, Map<String, Object> params);
    /**
     * 根据时间段查询
     * @param startDate
     * @param endDate
     * @param params
     * @return
     */
    PageUtils queryByDate(String startDate, String endDate, Map<String, Object> params);

    /**
     * 根据资产名称查询
     * @param assInforName
     * @return
     */
    AssetsInfo queryByName(String assInforName);

}
