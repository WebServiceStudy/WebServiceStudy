package com.wss.webservicestudy.web.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.StringJoiner;
import java.util.function.Supplier;

@ApiModel(description = "공통 응답객체")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    @ApiModelProperty(position = 0)
    private boolean success;
    @ApiModelProperty(position = 1)
    private String code;
    @ApiModelProperty(position = 2)
    private Supplier<T> result;
    @ApiModelProperty(position = 3)
    private String message;

    public ApiResponse(boolean success, String code, String message, Supplier<T> result) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public T getResult() {
        return result.get();
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApiResponse.class.getSimpleName() + "[", "]")
                .add("success=" + success)
                .add("code='" + code + "'")
                .add("result=" + result)
                .add("message=" + message)
                .toString();
    }


    public static ApiResponse<String> ok() {
        return ok("ok");
    }

    public static <T> ApiResponse<T> ok(T result) {
        return new ApiResponse<>(true, "200", "SUCCESS", () -> result);
    }

    public static <T> ApiResponse<T> message(String message, T result) {
        return new ApiResponse<>(true, "200", message, () -> result);
    }

    public static <T> ApiResponse<T> fail(String code, T result) {
        return new ApiResponse<>(false, code, "FAIL", () -> result);
    }

    public static <T> ApiResponse<T> fail(int code, T result) {
        return new ApiResponse<>(false, String.valueOf(code), "FAIL", () -> result);
    }

    public static <T> ApiResponse<T> fail(String code, String message, T result) {
        return new ApiResponse<>(false, code, message, () -> result);
    }

    public static <T> ApiResponse<T> fail(int code, String message, T result) {
        return new ApiResponse<>(false, String.valueOf(code), message, () -> result);
    }

}