package com.zdk.log;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnProperty(name = {"traceId.filter.enable"}, havingValue = "true")
public class FilterConfig {

    @Resource
    private TraceIdFilter traceIdFilter;

    @Bean
    public FilterRegistrationBean registrationFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        // 需要加入 traceId 的Filter
        registrationBean.setFilter(traceIdFilter);
        // 设置需要拦截的url
        registrationBean.addUrlPatterns("/*");
        // 设置名称
        registrationBean.setName("traceIdFilter");
        // 设置级别（越小越好）
        registrationBean.setOrder(1);
        return registrationBean;
    }

}