package com.lx.eims.service.impl.assets;
import com.lx.eims.entity.assets.AssetsCount;
import com.lx.eims.entity.assets.AssetsInfo;
import com.lx.eims.service.AssetsCategoryService;
import com.lx.eims.service.AssetsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * author: lixing
 * date: 2019-03-29
 * time: 17:33
 * description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AssetsServiceImplTest {

    @Autowired
    private AssetsService assetsService;

    @Autowired
    private AssetsCategoryService assetsCategoryService;

    /**
     * 测试资产全部查询
     */
    @Test
    public void assets(){
        List<AssetsInfo> list=assetsService.assetsList();
        System.out.println(list);
    }


   /** @Test
    public void asstesCategory(){
        List<Map<String, Object>> list=assetsCategoryService.getLargeCategory();
        System.out.println(list);
        Map<String, Object> map=new LinkedHashMap<>(16);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).get("large_category"));
            System.out.println(list.get(i).get("count"));
        }
        System.out.println(map);
    }*/
}