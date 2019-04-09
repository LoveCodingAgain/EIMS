package com.lx.eims.controller.contract;
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
 * date: 2019-04-02
 * time: 21:47
 * description:合同控制器
 */
@Controller
@RequestMapping("/contract")
public class ContractController {
    /**
     * 合同查询页面
     * @return
     */
    @RequestMapping("/search.html")
    public String search(){
        return "contract/search.html";
    }

    /**
     * 合同新增页面
     * @return
     */
    @RequestMapping("/add.html")
    public String add(){
        return "contract/add.html";
    }

    /**
     * 合同更新页面
     * @return
     */
    @RequestMapping("/update.html")
    public String update(){
        return "contract/update.html";
    }

}
