package com.mzj.dao.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 瑞冰 on 2017/11/28.
 */
public class CartProductDelete {
    private List<Integer> productIds = new ArrayList<Integer>();

    public CartProductDelete(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public CartProductDelete() {
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    @Override
    public String toString() {
        return "CartProductDelete{" +
                "productIds=" + productIds +
                '}';
    }
}
