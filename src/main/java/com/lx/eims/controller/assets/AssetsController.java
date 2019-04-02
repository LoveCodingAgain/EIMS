package com.lx.eims.controller.assets;
import com.lx.eims.entity.assets.AssetsCategory;
import com.lx.eims.entity.assets.AssetsCount;
import com.lx.eims.entity.assets.AssetsInfo;
import com.lx.eims.entity.assets.AssetsMessage;
import com.lx.eims.exception.GlobalException;
import com.lx.eims.service.AssetsCategoryService;
import com.lx.eims.service.AssetsService;
import com.lx.eims.util.Constant;
import com.lx.eims.util.Message;
import com.lx.eims.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-27
 * time: 22:55
 * description:资产管理控制器
 */
@Controller
@RequestMapping("/assets")
public class AssetsController {

     @Autowired
     private AssetsService assetsService;

     @Autowired
     private AssetsCategoryService assetsCategoryService;
    /**
     * 资产查询路由
     * @return
     */
    @RequestMapping("/search.html")
    public String search(){
        return "assets/search.html";
    }

    /**
     * 资产新增路由
     * @return
     */
    @RequestMapping("/add.html")
    public String add(){
        return "assets/add.html";
    }

    /**
     * 资产更新路由
     * @return
     */
    @RequestMapping("/update.html")
    public String update(){
        return "assets/update.html";
    }

    /**
     * 资产类目
     * @return
     */
    @RequestMapping("/category.html")
    public String dategory(){
        return "assets/category.html";
    }


    /**
     * 资产全部查询页面
     * @param params
     * @return
     */
    @RequestMapping("/search/list")
    @ResponseBody
    public Message listAssets(@RequestParam Map<String, Object> params){
        PageUtils page=assetsService.queryPage(params);
        return Message.ok().put("page", page);
    }

    /**
     * 资产条件类目查询
     * @param params
     * @return
     */
    @RequestMapping("/search/condition")
    @ResponseBody
    public Message conditionAssets(@RequestParam Map<String, Object> params){
        PageUtils conditionPage=assetsService.queryByCategory((String)params.get("category"),params);
        return Message.ok().put("page", conditionPage);
    }

    /**
     * 资产时间段查询
     * @param params
     * @return
     */
    @RequestMapping("/search/date")
    @ResponseBody
    public Message dateAssets(@RequestParam Map<String, Object> params){
        PageUtils datePage=assetsService.queryByDate((String)params.get("startDate"), (String)params.get("endDate"), params);
        return Message.ok().put("page", datePage);
    }

    /**
     * 资产类目
     * @param params
     * @return
     */
   @RequestMapping("/search/category")
   @ResponseBody
   public Message category(@RequestParam Map<String, Object> params){
       PageUtils categoryPage=assetsCategoryService.getLargeCategory(params);
       return Message.ok().put("page", categoryPage);
   }

    /**
     * 根据资产名称查询
     * @param assInforName
     * @return
     */
   @RequestMapping(value="/search/name",method = RequestMethod.POST, consumes = "application/json")
   @ResponseBody
   public AssetsInfo assetsInfo(@RequestBody String assInforName){
       return assetsService.queryByName(assInforName);
   }

    /**
     * 新增类目
     * @param assetsCategory
     * @return
     */
    @RequestMapping(value="/save/category",method = RequestMethod.POST, consumes = "application/json", produces="application/json")
    @ResponseBody
    public Message saveCategory(@RequestBody AssetsCategory assetsCategory) {
        boolean result=assetsCategoryService.save(assetsCategory);
        if(!result){
            throw new DuplicateKeyException("新增类目失败,有重复类目先查询检查一下！");
        }
        return Message.ok();
   }

    /**
     * 新增资产
     * @param assetsInfo
     * @return
     */
   @RequestMapping(value="/save/assets",method = RequestMethod.POST, consumes = "application/json")
   @ResponseBody
    public Message saveAssets(@RequestBody AssetsInfo assetsInfo){
       boolean result=assetsService.save(assetsInfo);
       if(!result){
           throw new  DuplicateKeyException("新增类目失败,有重复类目先查询检查一下！");
       }
       return Message.ok();
   }

    /**
     * 更新资源
     * @param assetsInfo
     * @return
     */
   @RequestMapping(value="/update/assets", method=RequestMethod.POST, consumes = "application/json")
   @ResponseBody
   public Message updateAssets(@RequestBody AssetsInfo assetsInfo){
       int result=assetsService.updateAssets(assetsInfo);
       if(result<1){
           throw new GlobalException("抱歉,修改资产失败,请重试!");
       }
       return Message.ok();
   }

    /**
     * 类目图表展示
     * @return
     */
   @RequestMapping(value="/charts/assets",produces = "application/json")
   @ResponseBody
   public List<AssetsCount> largeCategoty(){
       List<AssetsCount> assetsList=assetsCategoryService.getLargeCategory();
       return  assetsList;
   }
}
