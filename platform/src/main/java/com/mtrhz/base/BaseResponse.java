package com.mtrhz.base;

/**
 * 返回数据基础类
 * @author Solrsky
 * @date 2019/1/14
 */
public class BaseResponse {

    /**
     * 元数据
     */
    private Meta meta;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 成功，无返回数据
     * @param msg 返回信息
     * @return
     */
    public BaseResponse Success(String msg){
        meta = new Meta(true, msg);
        return this;
    }

    /**
     * 成功，有返回数据
     * @param data 返回数据
     * @param msg 返回信息
     * @return
     */
    public BaseResponse Success(Object data, String msg){
        meta = new Meta(true, msg);
        this.data = data;
        return this;
    }

    /**
     * 失败
     * @param msg 返回信息
     * @return
     */
    public BaseResponse fail(String msg){
        meta = new Meta(false, msg);
        return this;
    }

    /**
     * 包含code的失败返回信息
     * @param code 错误码
     * @param msg 返回信息
     * @return
     */
    public BaseResponse fail(String code, String msg){
        meta = new Meta(false, code, msg);
        return this;
    }

    /**
     * 元数据内部类
     */
    private class Meta{

        private boolean success;
        private String code;
        private String msg;

        public Meta(boolean success, String code, String msg) {
            this.success = success;
            this.code = code;
            this.msg = msg;
        }

        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success, String msg) {
            this.success = success;
            this.msg = msg;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

    }
}
