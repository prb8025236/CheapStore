package com.mzj.dao.idao;

import com.mzj.dao.vo.CartProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 瑞冰 on 2017/11/27.
 */
public interface CartProductMapper {
    /**
     * 需要登陆状态
     *
     * @return 返回购物车中的商品集合
     */
    List<CartProduct> cartFindAllProducts(int user_id) throws Exception;

    /**
     * @param productId
     * @param count
     *
     */
    void cartAddProduct(@Param("userId") int userId, @Param("productId") int productId, @Param("quantity") int count) throws Exception;

    /**
     * @param productId
     * @param count
     *
     */
    void cartUpdateNum(@Param("userId") int userId, @Param("productId") int productId, @Param("count") int count)throws Exception;


    void cartDeleteProducts(@Param("userId") int userId, @Param("productIds") List<Integer> productIds)throws Exception;

    void cartSelect(@Param("userId") int userId, @Param("productId") int productId)throws Exception;


    void cartUnSelect(@Param("userId") int userId, @Param("productId") int productId)throws Exception;

    int getCartProductNum(@Param("userId") int userId)throws Exception;


    void cartSelectAll(@Param("userId") int userId)throws Exception;


    void cartUnSelectAll(@Param("userId") int userId)throws Exception;
}
