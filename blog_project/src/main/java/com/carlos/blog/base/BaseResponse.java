package com.carlos.blog.base;


/**
 * @author Solrsky
 * @date 2018/12/4
 */
public class BaseResponse {

    /**
     * 标识请求是否成功，返回true or false
     */
    private boolean status;

    /**
     * 返回状态码，对应不同的请求结果
     */
    private Integer code;

    /**
     * 请求结果数据
     */
    private Object data;

    /**
     * 请求返回文字信息
     */
    private String msg;

    public BaseResponse() {
    }

    /**
     * 直接存数据,默认请求成功
     * @param data 返回数据
     */
    public BaseResponse(Object data) {
        this.data = data;
        this.status = true;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
