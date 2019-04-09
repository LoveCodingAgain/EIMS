package com.lx.eims.mapper.notice;
import com.lx.eims.entity.notice.NoticeCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @author: lixing
 * date: 2019-04-05
 * time: 16:50
 * @description:公告接口
 */
@Repository
public interface NoticeCustomMapper {
    /**
     * 获得公告总数
     */
    Integer countNotice(@Param(value = "status") Integer status) throws Exception;

    /**
     * 获得公告列表
     */
    List<NoticeCustom> listNotice(@Param(value = "status") Integer status) throws Exception;
}
