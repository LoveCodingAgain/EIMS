package com.lx.eims.controller.assets;

import com.lx.eims.service.AssetsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * author: lixing
 * date: 2019-03-30
 * time: 22:35
 * description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AssetsControllerTest {

    @Autowired
    private AssetsService assetsService;

    @Test
    public void assets(){
        System.out.println(assetsService.queryByName("POS机"));
    }

}