package com.meng.backenddemo.response;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // 无数据的响应
    public static <T> ApiResponse<T> messageOnly(int status, String message) {
        return new ApiResponse<>(status, message, null);
    }

    // 标准响应
    public static <T> ApiResponse<T> of(int status, String message, T data) {
        return new ApiResponse<>(status, message, data);
    }
}