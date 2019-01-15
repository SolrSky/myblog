package com.mtrhz.constant;

/**
 * 返回值枚举类
 * @author Solrsky
 * @date 2019/1/14
 */
public enum ResponseEnum {
    SUCCESS("X0000", "成功"),
    FAIL("X0001", "失败"),
    EXCEPTION("X9999", "系统异常");

    ResponseEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private String code;

    private String text;

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
