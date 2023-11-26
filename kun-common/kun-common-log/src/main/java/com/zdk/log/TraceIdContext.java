package com.zdk.log;

import org.slf4j.MDC;
import java.util.UUID;

/**
 *  存取TraceID的上下文
 */
public class TraceIdContext {

    // 由于ThreadLocal不能在父子线程中传递，所以此处使用InheritableThreadLocal
    public static final ThreadLocal<String> CURRENT_TRACE_ID = new InheritableThreadLocal<>();

    // 生成TraceId
    public static String generateTraceId() {
        // 每条请求给随机一个UUID(也可以做分布式ID)
        return UUID.randomUUID().toString();
    }

    // 获取TraceId
    public static String getTraceId() {
        return MDC.get(TraceIdConstant.TRACE_ID);
    }

    // 设置TraceId
    public static void setTraceId(String traceId) {
        MDC.put(TraceIdConstant.TRACE_ID, traceId);
    }

    // 清空TraceId
    public static void clearTraceId() {
        CURRENT_TRACE_ID.set(null);
        CURRENT_TRACE_ID.remove();
    }
}