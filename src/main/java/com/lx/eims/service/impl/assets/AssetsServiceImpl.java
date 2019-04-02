package com.lx.eims.service.impl.assets;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.eims.entity.assets.AssetsInfo;
import com.lx.eims.mapper.assets.AssetsMapper;
import com.lx.eims.service.AssetsService;
import com.lx.eims.util.PageUtils;
import com.lx.eims.util.QueryUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-29
 * time: 17:19
 * description:资产管理模块
 */
@Service
public class AssetsServiceImpl extends ServiceImpl<AssetsMapper, AssetsInfo> implements AssetsService {
    /**
     * 查询全部资产
     * @return
     */
    @Override
    public List<AssetsInfo> assetsList() {
        return this.baseMapper.selectList(null);
    }

    /**
     *  分页查询
      * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<AssetsInfo> page = this.page(
                new QueryUtils<AssetsInfo>().getPage(params),
                new QueryWrapper<AssetsInfo>().like(StringUtils.isNotBlank(key),"ass_infor_name", key)
        );
        return new PageUtils(page);
    }

    /**
     * 根据类目查询
     * @param category
     * @param params
     * @return
     */
    @Override
    public PageUtils queryByCategory(String category, Map<String, Object> params) {
        IPage<AssetsInfo> categoryPage = this.page(
                new QueryUtils<AssetsInfo>().getPage(params),
                new QueryWrapper<AssetsInfo>().like("large_category", category)
        );
        return new PageUtils(categoryPage);
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
        IPage<AssetsInfo> datePage = this.page(
                new QueryUtils<AssetsInfo>().getPage(params),
                new QueryWrapper<AssetsInfo>().between("ass_create_time",startDate,endDate)
        );
        return new PageUtils(datePage);
    }

    /**
     * 根据资源名称查询
     * @param assInforName
     * @return
     */
    @Override
    public AssetsInfo queryByName(String assInforName) {
        QueryWrapper<AssetsInfo> wrapper=new QueryWrapper<>();
        wrapper.eq("ass_infor_name", assInforName);
        return this.baseMapper.selectOne(wrapper);
    }

    /**
     * 更新资源(根据ID)
     * @param assetsInfo
     */
    @Override
    public int updateAssets(AssetsInfo assetsInfo) {
         return this.baseMapper.update(assetsInfo,
                 new QueryWrapper<AssetsInfo>().eq("ass_infor_id", assetsInfo.getAssInforId()));
    }
}
