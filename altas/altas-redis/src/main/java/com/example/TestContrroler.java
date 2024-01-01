package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
public class TestContrroler {
    @Value("${spring.application.name}")
    private String name ;
    @GetMapping("/{id}")
    public String getOrder(@PathVariable("id") Integer id ){
        log.info("getOrder111 name is "+name);
        return "19092"+id;
    }
}
