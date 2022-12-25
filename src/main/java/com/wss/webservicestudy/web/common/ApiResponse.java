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
    private Supplier<T> data;
    @ApiModelProperty(position = 3)
    private String message;

    public ApiResponse(boolean success, String code, String message, Supplier<T> data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public T getResult() {
        return data.get();
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApiResponse.class.getSimpleName() + "[", "]")
                .add("success=" + success)
                .add("code='" + code + "'")
                .add("data=" + data)
                .add("message=" + message)
                .toString();
    }


    public static ApiResponse<String> ok() {
        return ok("ok");
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, "200", "SUCCESS", () -> data);
    }

    public static <T> ApiResponse<T> message(String message, T data) {
        return new ApiResponse<>(true, "200", message, () -> data);
    }

    public static <T> ApiResponse<T> fail(String code, T data) {
        return new ApiResponse<>(false, code, "FAIL", () -> data);
    }

    public static <T> ApiResponse<T> fail(int code, T data) {
        return new ApiResponse<>(false, String.valueOf(code), "FAIL", () -> data);
    }

    public static <T> ApiResponse<T> fail(String code, String message, T data) {
        return new ApiResponse<>(false, code, message, () -> data);
    }

    public static <T> ApiResponse<T> fail(int code, String message, T data) {
        return new ApiResponse<>(false, String.valueOf(code), message, () -> data);
    }

}