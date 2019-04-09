package com.lx.eims.service.impl.notice;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.eims.entity.notice.Notice;
import com.lx.eims.entity.notice.NoticeCustom;
import com.lx.eims.mapper.notice.NoticeCustomMapper;
import com.lx.eims.mapper.notice.NoticeMapper;
import com.lx.eims.service.NoticeService;
import com.lx.eims.util.PageUtils;
import com.lx.eims.util.QueryUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-04-05
 * time: 17:02
 * description:公告接口实现
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private NoticeCustomMapper noticeCustomMapper;


    @Override
    public PageUtils getNotices(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<Notice> noticeIPage=this.page(
                new QueryUtils<Notice>().getPage(params),
                new QueryWrapper<Notice>().like(StringUtils.isNotBlank(key),"notice_id", key)
        );
        return new PageUtils(noticeIPage);
    }

    @Override
    public List<NoticeCustom> listNotice(Integer status) throws Exception {
        List<NoticeCustom> noticeList =noticeCustomMapper.listNotice(status);
        return noticeList;
    }


    @Override
    public int updateNotice(Notice notice)  {
        return this.baseMapper.update(notice, new QueryWrapper<Notice>().eq("notice_id",notice.getNoticeId()));
    }

    @Override
    public NoticeCustom getNoticeById(Integer id) throws Exception {
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        NoticeCustom noticeCustom=new NoticeCustom();
        BeanUtils.copyProperties(notice,noticeCustom);
        return noticeCustom;
    }
}
