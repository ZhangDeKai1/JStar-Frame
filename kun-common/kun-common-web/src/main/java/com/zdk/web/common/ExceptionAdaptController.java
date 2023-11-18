package com.zdk.web.common;

import com.zdk.web.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 异常捕获类
 */
@RestControllerAdvice
public class ExceptionAdaptController {
    /**
     * 运行异常捕获
     */
    @ExceptionHandler({RuntimeException.class})
    public Result runtimeException(RuntimeException exception) {
        exception.printStackTrace();
        return Result.fail(exception.getMessage());
    }

    /**
     * 其他异常捕获
     */
    @ExceptionHandler({Exception.class})
    public Result exception(Exception exception) {
        exception.printStackTrace();
        return Result.fail(exception.getMessage());
    }
}
