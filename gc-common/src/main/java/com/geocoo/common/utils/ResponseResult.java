package com.geocoo.common.utils;

/**
 * desc:
 * rest response result object
 * @author taopy
 * @create 2018-10-28 7:36 PM
 */
public class ResponseResult {
    private String msg;
    private boolean status;
    private String convertID;

    public ResponseResult(boolean status, String convertID, String msg) {
        this.msg = msg;
        this.status = status;
        this.convertID = convertID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean result) {
        this.status = result;
    }

    public String getConvertID() {
        return convertID;
    }

    public void setConvertID(String convertID) {
        this.convertID = convertID;
    }
}