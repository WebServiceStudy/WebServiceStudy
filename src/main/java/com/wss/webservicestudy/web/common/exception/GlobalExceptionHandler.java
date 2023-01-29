package com.wss.webservicestudy.web.common.exception;

import com.wss.webservicestudy.web.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final ApiResponse<Void> PAGE_NOT_FOUND_RESPONSE = new ApiResponse<Void>(false, "404", "페이지를 찾을 수 없습니다.", () -> null);


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Void> unexpectedException(Exception e) {
        log.error("UNEXPECTED SERVER EXCEPTION >>>>> ", e);
        return new ApiResponse<Void>(false, "500", "서버 오류가 발생했습니다 (" + e.getMessage() + ")", () -> null);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Void> expectedException(Exception e) {
        log.error("EXPECTED SERVER EXCEPTION >>>>> ", e);
        return new ApiResponse<Void>(false, "400", "요청 처리 실패", () -> null);
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Void> pageNotFoundException(NoHandlerFoundException e) {
        return PAGE_NOT_FOUND_RESPONSE;
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> dataException(Exception e) {
        return new ApiResponse<Void>(true, "400", "요청 데이터를 확인해주세요", () -> null);
    }

}
