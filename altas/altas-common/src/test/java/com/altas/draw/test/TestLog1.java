package com.altas.draw.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:log4j.xml" })
@Slf4j
public class TestLog1 {

    @Test
    public void testMDC() throws InterruptedException {
       MDC.put("traceId", UUID.randomUUID().toString());
        log.info("开始调用服务A，进行业务处理");
        log.info("业务处理完毕，可以释放空间了，避免内存泄露");
    	//MDC put 一定要对应一个 remove
       // MDC.remove("traceId");
        log.info("traceId {}", MDC.get("traceId"));
    }
}
