package com.mzj.service.iservice;

import com.mzj.dao.vo.CartServerResponse;
import com.mzj.dao.vo.CartServerResponse2;

import java.util.List;

/**
 * Created by 瑞冰 on 2017/11/27.
 */
public interface ICartService {
    /**
     *需要登陆状态
     * @return 返回购物车中的商品集合
     */
    CartServerResponse cartFindAllProducts(int user_id);

    /**
     *
     * @param productId
     * @param count
     * @return
     */
    CartServerResponse cartAddProduct(int userId, int productId, int count);

    /**
     *
     * @param productId
     * @param count
     * @return
     */
    CartServerResponse cartUpdateNum(int user_id, int productId, int count);

    /**
     *
     * @param
     * @param
     * @return
     */
    CartServerResponse cartDeleteProducts(int userId, List<Integer> productIds);

    /**
     *
     * @param productId
     * @return CartProduct
     */
    CartServerResponse cartSelect(int user_id, int productId);

    /**
     *
     * @param productId
     * @return CartProduct
     */
    CartServerResponse cartUnSelect(int user_id, int productId);

    /**
     *
     * @return num
     */
    CartServerResponse2 getCartProductNum(int user_id);

    /**
     *
     * @return
     */
    CartServerResponse cartSelectAll(int user_id);

    /**
     *
     * @return List<CartProduct>
     */
    CartServerResponse cartUnSelectAll(int user_id);

}
