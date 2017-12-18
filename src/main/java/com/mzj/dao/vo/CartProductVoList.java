package com.mzj.dao.vo;

import com.mzj.dao.pojo.Cart;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 瑞冰 on 2017/11/28.
 */
public class CartProductVoList {
    private List<CartProduct> cartProductVoList;
    private Boolean allCkecked;
    private BigDecimal cartTotalPrice;

    public CartProductVoList(List<CartProduct> cartProductVoList, Boolean allCkecked, BigDecimal cartTotalPrice) {
        this.cartProductVoList = cartProductVoList;
        this.allCkecked = allCkecked;
        this.cartTotalPrice = cartTotalPrice;
    }

    public CartProductVoList() {
    }

    public List<CartProduct> getCartProductVoList() {
        return cartProductVoList;
    }

    public void setCartProductVoList(List<CartProduct> cartProductVoList) {
        this.cartProductVoList = cartProductVoList;
    }

    public Boolean getAllCkecked() {
        return allCkecked;
    }

    public void setAllCkecked(Boolean allCkecked) {
        this.allCkecked = allCkecked;
    }

    public BigDecimal getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(BigDecimal cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    @Override
    public String toString() {
        return "CartProductVoList{" +
                "cartProductVoList=" + cartProductVoList +
                ", allCkecked=" + allCkecked +
                ", cartTotalPrice=" + cartTotalPrice +
                '}';
    }
}
