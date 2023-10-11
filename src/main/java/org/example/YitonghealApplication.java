package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
@MapperScan("org.example.dao")
public class YitonghealApplication {
    public static void main(String[] args) {
        SpringApplication.run(YitonghealApplication.class,args);
    }
}
