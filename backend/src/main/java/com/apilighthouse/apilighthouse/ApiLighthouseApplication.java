package com.apilighthouse.apilighthouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.apilighthouse.apilighthouse.mapper")
public class ApiLighthouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiLighthouseApplication.class, args);
    }
}
