package com.lx.eims.service.impl.data;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.eims.entity.data.Data;
import com.lx.eims.mapper.data.DataMapper;
import com.lx.eims.service.DataService;
import com.lx.eims.util.PageUtils;
import com.lx.eims.util.QueryUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-04-09
 * time: 0:17
 * description:
 */
@Service
public class DataServiceImpl extends ServiceImpl<DataMapper,Data> implements DataService {
    /**
     * 查询数据明细
     * @param params
     * @return
     */
    @Override
    public PageUtils getDatas(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<Data> noticeIPage=this.page(
                new QueryUtils<Data>().getPage(params),
                new QueryWrapper<Data>().like(StringUtils.isNotBlank(key),"data_id", key)
        );
        return new PageUtils(noticeIPage);
    }

    /**
     * 查询全部数据
     * @return
     */
    @Override
    public List<Data> dataList() {
        List<Data> dataList=this.baseMapper.selectList(null);
        return dataList;
    }
}
