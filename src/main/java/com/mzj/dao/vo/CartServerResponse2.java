package com.mzj.dao.vo;


import com.mzj.commons.ServerResponse;

/**
 * Created by 瑞冰 on 2017/11/28.
 */
public class CartServerResponse2 extends ServerResponse<Integer> {
    public CartServerResponse2(int status) {
        super(status);
    }

    public CartServerResponse2(int status, Integer data) {
        super(status, data);
    }

    public CartServerResponse2(int status, String msg) {
        super(status, msg);
    }

    public CartServerResponse2(int status, String msg, Integer data) {
        super(status, msg, data);
    }
}
