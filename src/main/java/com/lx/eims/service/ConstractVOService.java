package com.lx.eims.service;
import com.lx.eims.entity.contract.ConstractVO;

import java.util.Map;

/**
 * author: lixing
 * date: 2019-04-10
 * time: 15:46
 * description:
 */
public interface ConstractVOService {
    /**
     * 查询合同
     * @param constractId
     * @return
     */
    Map<String, Object> getConstarct(Integer constractId);
}
