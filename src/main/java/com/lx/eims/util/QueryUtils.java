package com.lx.eims.util;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.eims.config.SQLFilter;
import org.apache.commons.lang.StringUtils;
import java.util.Map;
/**
 * author: lixing
 * date: 2019-03-15
 * time: 17:21
 * description:查询参数工具类
 */
public class QueryUtils<T> {

    public IPage<T> getPage(Map<String, Object> params) {
        return this.getPage(params, null, false);
    }
    /**
     * 排序字段返回分页结果
     * @param params
     * @param defaultOrderField
     * @param isAsc
     * @return
     */
    public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        //分页参数
        long curPage = 1;
        long limit = 10;
        if(params.get(Constant.PAGE) != null){
            curPage = Long.parseLong((String)params.get(Constant.PAGE));
        }
        if(params.get(Constant.LIMIT) != null){
            limit = Long.parseLong((String)params.get(Constant.LIMIT));
        }
        //分页对象
        Page<T> page = new Page<>(curPage, limit);
        //分页参数
        params.put(Constant.PAGE, page);
        //排序字段
        //防止SQL注入（因为sidx,order是通过拼接SQL实现排序的,会有SQL注入风险）
        String orderField = SQLFilter.sqlInject((String)params.get(Constant.ORDER_FIELD));
        String order = (String)params.get(Constant.ORDER);
        //前端字段排序
        if(StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)){
            if(Constant.ASC.equalsIgnoreCase(order)) {
                return page.setAsc(orderField);
            }else {
                return page.setDesc(orderField);
            }
        }
        //默认排序
        if(isAsc) {
            page.setAsc(defaultOrderField);
        }else {
            page.setDesc(defaultOrderField);
        }
        return page;
    }
}
