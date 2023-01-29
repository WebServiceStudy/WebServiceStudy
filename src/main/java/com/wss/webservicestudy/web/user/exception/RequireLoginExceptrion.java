package com.wss.webservicestudy.web.user.exception;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class RequireLoginExceptrion extends BaseException {
    private static final HttpStatus STATUS = HttpStatus.UNAUTHORIZED;
    private static final String MESSAGE = "로그아웃 된 사용자입니다.";
    private static final ApiResponse<Void> RESPONSE = new ApiResponse<>(false, String.valueOf(STATUS.value()), MESSAGE, () -> null);
    private static final AlreadyExistUserException INSTANCE = new AlreadyExistUserException();

    public RequireLoginExceptrion() {
    }

    @Override
    public HttpStatus getStatus() {
        return STATUS;
    }

    @Override
    public String getErrorCode() {
        return String.valueOf(STATUS.value());
    }
    @Override
    public String getErrorMessage() {
        return MESSAGE;
    }

    @Override
    public ApiResponse<Void> getErrorResponse() {
        return RESPONSE;
    }

    public static BaseException getInstance() {
        return INSTANCE;
    }
}
