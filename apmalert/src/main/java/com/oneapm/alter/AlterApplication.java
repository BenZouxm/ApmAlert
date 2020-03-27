package com.oneapm.alter;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan("com.oneapm.alter")
@MapperScan("com.oneapm.alter.dao")
@SpringBootApplication
public class AlterApplication {

    public static void main(String[] args) {
        try {
            log.error("application is starting：");
            SpringApplication.run(AlterApplication.class, args);
        } catch (Exception e) {
            log.error("Env config Run：", e);
            throw e;
        }
    }
}
