package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器
 */
@Slf4j
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean shiroFilterRegistration() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //注入过滤器
        registrationBean.setFilter(new ShiroFilter());
        //过滤器名称
        registrationBean.setName("ShiroFilter");
        //拦截规则
        registrationBean.addUrlPatterns("/*");
        //过滤器顺序
        registrationBean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);

        log.info("开启shiro-Filter过滤器。");

        return registrationBean;
    }
}
