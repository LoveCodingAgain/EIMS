package com.lx.eims.service.impl.system;
import com.lx.eims.entity.system.SysLog;
import com.lx.eims.service.SysLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Map;

/**
 * author: lixing
 * date: 2019-03-26
 * time: 19:41
 * description:日志测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysLogServiceImplTest {

    @Autowired
    private SysLogService sysLogService;

    /*@Test
    public void testSysLog(){
        List<SysLog> sysLogs=sysLogService.queryByOperate("员工登录");
        System.out.println("测试结果:"+sysLogs.size());
    }**/

    @Test
    public void listSysLog(){
        List<SysLog> list=sysLogService.logList();
        System.out.println(list.size());
    }

}