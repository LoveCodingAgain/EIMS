package com.lx.eims.service.impl.constract;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.eims.entity.contract.Constract;
import com.lx.eims.mapper.constract.ConstractMapper;
import com.lx.eims.service.ConstractService;
import com.lx.eims.util.PageUtils;
import com.lx.eims.util.QueryUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-04-09
 * time: 20:52
 * description:合同业务层
 */
@Service
public class ConstractServiceImpl extends ServiceImpl<ConstractMapper, Constract> implements ConstractService {
    /**
     * 分页查询全部
     * @param params
     * @return
     */
    @Override
    public PageUtils getConstracts(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<Constract> constractIPage=this.page(
                new QueryUtils<Constract>().getPage(params),
                new QueryWrapper<Constract>().like(StringUtils.isNotBlank(key),"constract_id", key)
        );
        return new PageUtils(constractIPage);
    }

    /**
     * 更新合同
     * @param constract
     * @return
     */
    @Override
    public int updateConstract(Constract constract) {
        return this.baseMapper.update(constract,
                new QueryWrapper<Constract>().eq("constract_id", constract.getConstractId()));
    }

    @Override
    public List<Constract> dataList() {
        List<Constract> list= this.baseMapper.selectList(null);
        return list;
    }


}
