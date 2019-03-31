package com.lx.eims.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.eims.entity.assets.AssetsCategory;
import com.lx.eims.util.PageUtils;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-30
 * time: 12:50
 * description:资产类目
 */
public interface AssetsCategoryService extends IService<AssetsCategory> {
    /**
     * 全部类目查询
     * @return
     */
    PageUtils getLargeCategory(Map<String, Object> params);

}
