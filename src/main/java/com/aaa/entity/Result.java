package com.aaa.entity;

import lombok.Data;

/**
 * @author ：xiaoliu
 * @date ：Created in 2020/6/23 15:25
 * @description：通用返回的实体
 * @modified By：
 * @version:
 */
@Data
public class Result<T> {
    private boolean status;
    private String msg;
    private T data;

    public Result(boolean status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
