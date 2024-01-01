package com.altas.draw.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class TestLog {
    private static Logger logger = LoggerFactory.getLogger(TestLog.class);
    @Test
    public void test(){
        log.info("asdfasdf");
        logger.info("123123123");
    }
    public static void main(String[] args){
        log.info("asdfasdf");
        log.info("bbbbbbbb");
        log.info("ccccccc");
        log.info("asdfasdf");
        logger.info("123123123");
    }
}
