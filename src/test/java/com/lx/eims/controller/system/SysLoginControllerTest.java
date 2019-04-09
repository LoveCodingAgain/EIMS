package com.lx.eims.controller.system;

import com.lx.eims.service.NoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * author: lixing
 * date: 2019-04-06
 * time: 20:51
 * description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysLoginControllerTest {

    @Autowired
    private NoticeService noticeService;

    @Test
    public void testListNotice() throws Exception {
        System.out.println(noticeService.listNotice(1));
    }
}