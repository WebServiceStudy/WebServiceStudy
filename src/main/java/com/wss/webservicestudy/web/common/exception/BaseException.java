package com.wss.webservicestudy.web.common.exception;

import com.wss.webservicestudy.web.common.ApiResponse;
import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {
    public abstract HttpStatus getStatus();
    public abstract String getErrorCode();
    public abstract String getErrorMessage();
    public abstract ApiResponse<Void> getErrorResponse();

    @Override
    public String getMessage() {
        return getErrorMessage();
    }
}
