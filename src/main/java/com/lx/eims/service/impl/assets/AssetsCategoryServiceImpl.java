package com.lx.eims.service.impl.assets;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.eims.entity.assets.AssetsCategory;
import com.lx.eims.entity.assets.AssetsCount;
import com.lx.eims.mapper.assets.AssetsCategoryMapper;
import com.lx.eims.service.AssetsCategoryService;
import com.lx.eims.util.PageUtils;
import com.lx.eims.util.QueryUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-30
 * time: 12:53
 * description:资产类目查询
 */
@Service
public class AssetsCategoryServiceImpl extends ServiceImpl<AssetsCategoryMapper, AssetsCategory> implements AssetsCategoryService {

    /**
     * 全部类目
     * @param params
     * @return
     */
    @Override
    public PageUtils getLargeCategory(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<AssetsCategory> page = this.page(
                new QueryUtils<AssetsCategory>().getPage(params),
                new QueryWrapper<AssetsCategory>().like(StringUtils.isNotBlank(key),"assets_id", key)
        );
        return new PageUtils(page);
    }

    @Override
    public List<AssetsCount> getLargeCategory() {
        return this.baseMapper.categoryGroup();
    }
}
