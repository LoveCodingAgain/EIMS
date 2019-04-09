package com.lx.eims.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.eims.entity.notice.Notice;
import com.lx.eims.entity.notice.NoticeCustom;
import com.lx.eims.util.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @author: lixing
 * date: 2019-04-05
 * time: 16:40
 * description:
 */
public interface NoticeService extends IService<Notice> {
    /**
     * 系统后台公告接口
     * @return
     */
    PageUtils getNotices(Map<String, Object> params);
    /**
     * 获得公告列表
     */
    List<NoticeCustom> listNotice(Integer status) throws Exception;

    /**
     * 更新公告
     */
    int updateNotice(Notice notice) ;

    /**
     * 根据id查询公告
     */
    NoticeCustom getNoticeById(Integer id) throws Exception;
}
