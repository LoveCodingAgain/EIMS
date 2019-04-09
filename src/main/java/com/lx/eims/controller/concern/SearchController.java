package com.lx.eims.controller.concern;
import com.lx.eims.util.HtmlUtils;
import org.apache.struts2.json.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-04-05
 * time: 10:53
 * description:员工查询
 */
@Controller
@RequestMapping("/concern")
public class SearchController {
    /**
     * search的查询路由
     * @return
     */
    @RequestMapping("/search.html")
    public String search(){
        return "/concern/search.html";
    }

    /**
     * 处理职位信息json数据返回
     */
    @RequestMapping("/dataResult")
    public void dataResult(HttpServletRequest request, HttpServletResponse response) {
        try {
            //处理Post的中文乱码
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            //处理前台提交的Ajax请求,返回给前台Json格式的数据
            String url=request.getParameter("url");
            //重构url链接,可以选择加密URLEncoder两次加密
            url= URLEncoder.encode(URLEncoder.encode(url,"UTF-8"),"UTF-8");
            url="https://search.51job.com/list/060000,000000,0000,00,9,##,"+url+",2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=21&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
            Map<String,Integer> map= HtmlUtils.getRange(url);
            PrintWriter out=response.getWriter();
            out.print("var objs = "+ JSONUtil.serialize(map)+";");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
