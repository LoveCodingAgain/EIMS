package com.lx.eims.service.impl.assets;
import com.lx.eims.entity.assets.AssetsInfo;
import com.lx.eims.service.AssetsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
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

    /**
     * 测试资产全部查询
     */
    @Test
    public void assets(){
        List<AssetsInfo> list=assetsService.assetsList();
        System.out.println(list);
    }


}