package com.mzj.commons;

/**
 * Created by Administrator on 2017/11/28.
 */
public enum ResponseCode {
   SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(2,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(3,"ILLEGAL_ARGUMENT");
    private final Integer code;
    private final String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    ResponseCode(Integer code, String desc) {

        this.code = code;
        this.desc = desc;
    }
}
