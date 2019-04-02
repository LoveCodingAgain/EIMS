package com.lx.eims.mapper.assets;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.eims.entity.assets.AssetsCategory;
import com.lx.eims.entity.assets.AssetsCount;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-30
 * time: 12:18
 * description:类目查询
 */
@Repository
public interface AssetsCategoryMapper extends BaseMapper<AssetsCategory> {
    /**
     * 类目分组查询
     * @return
     */
    List<AssetsCount> categoryGroup();
}
