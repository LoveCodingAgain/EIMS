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
     * 删除
     * description:公告Mpaper.
     * @param noticeId
     * @return
     */
    int deleteByPrimaryKey(Integer noticeId);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(Notice record);

    /**
     *
      * @param record
     * @return
     */
    int insertSelective(Notice record);

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
