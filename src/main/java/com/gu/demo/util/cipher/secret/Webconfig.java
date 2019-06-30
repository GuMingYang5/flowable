package com.gu.demo.util.cipher.secret;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: guMingYang
 * @DATE: 2019/5/19
 */

@Configuration
public class Webconfig {
    @Bean
    public FilterRegistrationBean WebFilterDemo(){
        //配置过滤器
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new ServletFilterParam());
        frBean.addUrlPatterns("/*");
        frBean.setName("ServletFilterParam");
        //springBoot会按照order值的大小，从小到大的顺序来依次过滤。
        frBean.setOrder(0);
        return frBean;
    }
}
