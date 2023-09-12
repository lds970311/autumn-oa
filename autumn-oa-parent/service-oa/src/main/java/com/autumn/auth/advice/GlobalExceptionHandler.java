package com.autumn.auth.advice;

import com.autumn.auth.exception.AutumnException;
import com.autumn.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<String> error(Exception e) {
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(AutumnException.class)
    @ResponseBody
    public Result<Object> error(AutumnException e) {
        log.error(e.getMessage());
        return Result.fail()
                .message(e.getMessage())
                .code(e.getCode());
    }
}
