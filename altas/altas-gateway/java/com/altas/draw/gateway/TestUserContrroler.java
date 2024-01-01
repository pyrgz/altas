package com.altas.draw.gateway;

import com.altas.draw.log.trace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@Slf4j
public class TestUserContrroler {
    @Value("${spring.application.name}")
    private String name ;
    @GetMapping("/{id}")
    @LogTrace(input = true,output = true)
    public String getUser(@PathVariable("id") Integer id ){
        log.info("getUser name is "+name);
        return "123123"+id;
    }
}
