package com.mzj.dao.vo;

import com.mzj.commons.ServerResponse;

/**
 * Created by 瑞冰 on 2017/11/28.
 */
public class CartServerResponse extends ServerResponse<CartProductVoList> {
    public CartServerResponse(int status) {
        super(status);
    }

    public CartServerResponse(int status, CartProductVoList data) {
        super(status, data);
    }

    public CartServerResponse(int status,String msg) {
        super(status,msg);
    }
    public CartServerResponse(int status, String msg, CartProductVoList data) {
        super(status, msg, data);
    }
}
