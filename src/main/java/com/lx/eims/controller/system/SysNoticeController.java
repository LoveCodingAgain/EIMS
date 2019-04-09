package com.lx.eims.controller.system;
import com.lx.eims.entity.notice.Notice;
import com.lx.eims.exception.GlobalException;
import com.lx.eims.service.NoticeService;
import com.lx.eims.util.Message;
import com.lx.eims.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-04-07
 * time: 21:08
 * description:系统公告管理
 */
@Controller
@RequestMapping("/sys")
public class SysNoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 系统的公告路由
     * @return
     */
    @RequestMapping("/notice.html")
    public String notice(){
        return "admin/notice.html";
    }

    /**
     * 系统公告
     * @param params
     * @return
     */
    @RequestMapping("/notice/list")
    @ResponseBody
    public Message list(@RequestParam Map<String, Object> params){
        PageUtils page=noticeService.getNotices(params);
        return Message.ok().put("page", page);
    }

    /**
     * 系统新增公告
     * @param notice
     * @return
     */
    @RequestMapping(value="/notice/save",method = RequestMethod.POST, consumes = "application/json", produces="application/json")
    @ResponseBody
    public Message save(@RequestBody Notice notice){
        boolean result= noticeService.save(notice);
        if(!result){
            throw new DuplicateKeyException("新增公告失败,有重复公告先查询检查一下！");
        }
        return Message.ok();
    }

    /**
     * 更新公告
     * @param notice
     * @return
     */
    @RequestMapping(value="/notice/update", method=RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Message  updateNotice(@RequestBody Notice notice){
        int result=noticeService.updateNotice(notice);
        if(result<1){
            throw new GlobalException("抱歉,修改公告失败,请重试!");
        }
        return Message.ok();
    }

}
