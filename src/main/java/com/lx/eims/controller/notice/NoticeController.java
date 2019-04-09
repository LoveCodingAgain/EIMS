package com.lx.eims.controller.notice;
import com.lx.eims.entity.notice.NoticeCustom;
import com.lx.eims.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
/**
 * @author: lixing
 * date: 2019-04-05
 * time: 17:00
 * description:
 */
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/notice/details")
    public ModelAndView NoticeDetailView(@RequestParam("noticeId") Integer noticeId) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        NoticeCustom noticeCustom  = noticeService.getNoticeById(noticeId);
        modelAndView.addObject("noticeCustom",noticeCustom);
        modelAndView.setViewName("notice/detail");
        return modelAndView;
    }
}
