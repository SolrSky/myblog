package com.carlos.blog.constant;

/**
 * @author Solrsky
 * @date 2018/12/10
 */
public enum ResponseEnum {

    SUCCESS(1000, "成功"),
    FAIL(1001, "失败"),
    EXCEPTION(9999, "系统异常");

    ResponseEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    private Integer value;

    private String text;

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
