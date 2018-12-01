package com.wangbin.hello.spring.boot.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.wangbin.hello.spring.boot.shiro.mapper")
public class HelloSpringBootShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringBootShiroApplication.class, args);
    }
}
