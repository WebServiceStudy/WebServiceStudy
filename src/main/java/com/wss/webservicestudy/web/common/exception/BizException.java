package com.wss.webservicestudy.web.common.exception;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.user.exception.AlreadyExistUserException;
import org.springframework.http.HttpStatus;

public class BizException extends BaseException {

    private final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    private String MESSAGE = "";

    private final ApiResponse<Void> RESPONSE = new ApiResponse<>(false, String.valueOf(this.STATUS.value()), this.MESSAGE, () -> null);

    private static final BizException INSTANCE = new BizException();

    public BizException() {
        this.MESSAGE = "요청 처리에 실패하였습니다";
    }

    public BizException(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    @Override
    public HttpStatus getStatus() {
        return this.STATUS;
    }

    @Override
    public String getErrorCode() {
        return String.valueOf(this.STATUS.value());
    }

    @Override
    public String getErrorMessage() {
        return this.MESSAGE;
    }

    @Override
    public ApiResponse<Void> getErrorResponse() {
        return RESPONSE;
    }
}
