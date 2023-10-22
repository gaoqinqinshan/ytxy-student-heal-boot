package org.example;

import cn.hutool.extra.mail.MailUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
//@EnableAsync //开启异步支持
//@EnableScheduling  //开启定时任务

//@MapperScan("org.example.dao")
public class YitonghealApplication {
    public static void main(String[] args) {
        SpringApplication.run(YitonghealApplication.class, args);
//        MailUtil.send("840265871@qq.com", "这是一个测试邮件的代码", "hutool  test", false);
    }
}
