package com.arvin.megacitycab.api.error;

public class ApiError {

    private int status = -1;
    private String msg;

    public int getStatus() {
        return status;
    }

    public ApiError(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ApiError(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
