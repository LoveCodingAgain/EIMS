package com.lx.eims.service.impl.constract;
import com.lx.eims.mapper.constract.ConstractVOMapper;
import com.lx.eims.service.ConstractVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-04-10
 * time: 15:48
 * description:合同查询
 */
@Service
public class ConstarctVOServiceImpl implements ConstractVOService {

    @Autowired
    private ConstractVOMapper constractVOMapper;

    @Override
    public Map<String, Object> getConstarct(Integer constractId) {
        return constractVOMapper.getConstractVOById(constractId);
    }

}
