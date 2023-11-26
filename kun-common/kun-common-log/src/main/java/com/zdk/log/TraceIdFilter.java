package com.zdk.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Component
@Slf4j
// 会导致自动注入到Bean中，然后自动触发，所以也在此加上@ConditionalOnProperty来进行控制
@ConditionalOnProperty(name = {"traceId.filter.enable"}, havingValue = "true")
public class TraceIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String traceId = httpServletRequest.getHeader(TraceIdConstant.TRACE_ID);
        // 如果TraceId为空，则赋值(可能多微服务调用，会从上游Http中获取)
        if (StringUtils.isBlank(traceId)) {
            // 生成TraceId
            traceId = TraceIdContext.generateTraceId();
        }
        // 将TraceId设置进MDC
        TraceIdContext.setTraceId(traceId);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ServletException e) {
            e.printStackTrace();
            log.error("TraceIdFilter:error:{}",e.getMessage(),e);
        }
        // 清空当前线程的TraceId
        TraceIdContext.clearTraceId();
    }
}