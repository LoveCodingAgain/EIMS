package com.lx.eims.service.impl.constract;
import com.lx.eims.service.ConstractVOService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * author: lixing
 * date: 2019-04-10
 * time: 15:50
 * description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ConstarctVOServiceImplTest {

    @Autowired
    private ConstractVOService constractVOService;

    @Test
    public void testGetConstarct(){
        System.out.println(constractVOService.getConstarct(2));
    }
}