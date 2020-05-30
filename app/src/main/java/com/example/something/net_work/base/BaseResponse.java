package com.example.something.net_work.base;

public class BaseResponse<T> {
    private int errorCode;
    private String errorMsg;
    private T data;

    public BaseResponse(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg == null ? "" : errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getBean() {
        return data;
    }

    public void setBean(T bean) {
        this.data = bean;
    }
}
