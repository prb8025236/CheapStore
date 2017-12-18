package com.mzj.service.impl;


import com.mzj.dao.idao.CartProductMapper;
import com.mzj.dao.idao.ProductMapper;
import com.mzj.dao.vo.CartProduct;
import com.mzj.dao.vo.CartProductVoList;
import com.mzj.dao.vo.CartServerResponse;
import com.mzj.dao.vo.CartServerResponse2;
import com.mzj.service.iservice.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by 瑞冰 on 2017/11/27.
 */
@Service("cartService")
public class CartServiceImpl implements ICartService {
    @Autowired
    CartProductMapper cartProductDao;
    @Autowired
    ProductMapper productDao;

    @Override
    @Transactional(readOnly = true)
    public CartServerResponse cartFindAllProducts(int user_id) {
        List<CartProduct> cartProducts = new ArrayList<>();
        CartProductVoList cartProductVoList = new CartProductVoList();
        Boolean allChecked = true;
        double cartTotalPrice = 0.00;
        try {
            cartProducts = cartProductDao.cartFindAllProducts(user_id);
            for (CartProduct cartProduct : cartProducts) {
                if (cartProduct.getQuantity() > cartProduct.getProductStock()) {
                    cartProduct.setLimitQuantity("LIMIT_NUM_ERROR");
                } else {
                    cartProduct.setLimitQuantity("LIMIT_NUM_SUCCESS");
                }
                if (cartProduct.getProductChecked() == 1) {
                    cartTotalPrice += cartProduct.getProductTotalPrice().doubleValue();
                }
                if (cartProduct.getProductChecked() == 0) {
                    allChecked = false;
                }
            }
            cartProductVoList.setCartProductVoList(cartProducts);
            cartProductVoList.setAllCkecked(allChecked);
            cartProductVoList.setCartTotalPrice(BigDecimal.valueOf(cartTotalPrice));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CartServerResponse(0,cartProductVoList);
    }

    @Override
    @Transactional
    public CartServerResponse cartAddProduct(int userId, int productId, int count) {
        List<CartProduct> cartProducts = new ArrayList<>();
        Boolean flag = true;
        try {
            cartProducts = cartProductDao.cartFindAllProducts(userId);
            if (cartProducts.isEmpty()) {
                cartProductDao.cartAddProduct(userId, productId, count);
            } else {
                for (CartProduct cartProduct : cartProducts) {
                    if (cartProduct.getProductId() == productId) {
                        cartProduct.setQuantity(cartProduct.getQuantity() + count);
                        cartUpdateNum(userId, productId, cartProduct.getQuantity());
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    cartProductDao.cartAddProduct(userId, productId, count);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartFindAllProducts(userId);
    }

    @Override
    @Transactional
    public CartServerResponse cartUpdateNum(int userId, int productId, int count) {
        try {
            cartProductDao.cartUpdateNum(userId, productId, count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartFindAllProducts(userId);
    }

    @Override
    @Transactional
    public CartServerResponse cartDeleteProducts(int userId, List<Integer> productIds) {
        try {
            cartProductDao.cartDeleteProducts(userId, productIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartFindAllProducts(userId);
    }

    @Override
    @Transactional
    public CartServerResponse cartSelect(int userId, int productId) {
        try {
            cartProductDao.cartSelect(userId, productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartFindAllProducts(userId);
    }

    @Override
    @Transactional
    public CartServerResponse cartUnSelect(int userId, int productId) {
        try {
            cartProductDao.cartUnSelect(userId, productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartFindAllProducts(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public CartServerResponse2 getCartProductNum(int userId) {
        Integer num = 0;
        try {
            num = cartProductDao.getCartProductNum(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CartServerResponse2(0,num);
    }

    @Override
    @Transactional
    public CartServerResponse cartSelectAll(int user_id) {
        try {
            cartProductDao.cartSelectAll(user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartFindAllProducts(user_id);
    }

    @Override
    @Transactional
    public CartServerResponse cartUnSelectAll(int user_id) {
        try {
            cartProductDao.cartUnSelectAll(user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartFindAllProducts(user_id);
    }
}
