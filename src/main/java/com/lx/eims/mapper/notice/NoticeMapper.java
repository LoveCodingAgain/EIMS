package com.lx.eims.mapper.notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.eims.entity.notice.Notice;
import org.springframework.stereotype.Repository;

/**
 * @author: lixing
 * date: 2019-04-05
 * time: 16:26
 */
@Repository
public interface NoticeMapper extends BaseMapper<Notice> {


    /**
     * 主键查询
     * @param noticeId
     * @return
     */
    Notice selectByPrimaryKey(Integer noticeId);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Notice record);

    /**
     * 主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Notice record);
}
