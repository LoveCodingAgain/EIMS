package com.lx.eims.mapper.constract;
import com.lx.eims.entity.contract.ConstractVO;
import org.springframework.stereotype.Repository;

import java.util.Map;


/**
 * @author: lixing
 * date: 2019-04-10
 * time: 15:34
 * description:合同关系
 */
@Repository
public interface ConstractVOMapper {
    /**
     * 根据合同ID查询信息
     * @param constractId
     * @return
     */
    Map<String, Object> getConstractVOById(int constractId);
}
