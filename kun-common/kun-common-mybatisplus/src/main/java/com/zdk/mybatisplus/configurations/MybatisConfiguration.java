package com.zdk.mybatisplus.configurations;

import com.zdk.mybatisplus.interceptor.SqlBeautyInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis配置类
 */
@Configuration
public class MybatisConfiguration {

    /**
     * 把sql优化器注入Bean容器
     * @return
     */
    @Bean
    @ConditionalOnProperty(value = {"sql.beauty.show"}, havingValue = "true", matchIfMissing = true)//在配置类中不配置或者配置为true时会打印SQL日志，但是配置为false就不再打印日志
    public SqlBeautyInterceptor sqlBeautyInterceptor(){
        return new SqlBeautyInterceptor();
    }

}
