package com.mzj.action;


import com.mzj.commons.Const;
import com.mzj.dao.pojo.User;
import com.mzj.dao.vo.CartProductDelete;
import com.mzj.dao.vo.CartServerResponse;
import com.mzj.dao.vo.CartServerResponse2;
import com.mzj.service.iservice.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by 瑞冰 on 2017/11/28.
 */
@Controller("cartAction")
@Scope("prototype")
@RequestMapping("/cart/")
public class CartAction {
    @Autowired
    private ICartService cartService;
    @RequestMapping(value = "list.do",method = RequestMethod.POST)
    public @ResponseBody
    CartServerResponse cartFindAllProducts(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        CartServerResponse cartServerResponse = new CartServerResponse(10,"用户未登录,请登录");
        if(user != null){
            cartServerResponse = cartService.cartFindAllProducts(user.getId());
        }
        return cartServerResponse;
    }
    @RequestMapping(value = "add.do",method = RequestMethod.POST)
    public @ResponseBody CartServerResponse cartAddProduct(int productId,int count,HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        CartServerResponse cartServerResponse = new CartServerResponse(10,"用户未登录,请登录");
        if(user != null){
            cartServerResponse = cartService.cartAddProduct(user.getId(),productId,count);
        }
        return cartServerResponse;
    }
    @RequestMapping(value = "update.do",method = RequestMethod.POST)
    public @ResponseBody CartServerResponse cartUpdateNum(int productId,int count,HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        CartServerResponse cartServerResponse = new CartServerResponse(10,"用户未登录,请登录");
        if(user != null){
            cartServerResponse = cartService.cartUpdateNum(user.getId(),productId,count);
        }
        return cartServerResponse;
    }
    @RequestMapping(value = "delete_product.do",method = RequestMethod.POST)
    public @ResponseBody CartServerResponse cartDeleteProducts(CartProductDelete productDeleteList, HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        CartServerResponse cartServerResponse = new CartServerResponse(10,"用户未登录,请登录");
        if(user != null){
            cartServerResponse = cartService.cartDeleteProducts(user.getId(),productDeleteList.getProductIds());
        }
        return cartServerResponse;
    }
    @RequestMapping(value = "get_cart_product_count.do",method = RequestMethod.POST)
    public @ResponseBody
    CartServerResponse2 getCartProductNum(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        CartServerResponse2 cartServerResponse = new CartServerResponse2(10,"出现异常");
        if(user != null){
            cartServerResponse = cartService.getCartProductNum(user.getId());
        }
        return cartServerResponse;
    }
    @RequestMapping(value = "select.do",method = RequestMethod.POST)
    public @ResponseBody
    CartServerResponse cartSelect(int productId,HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        CartServerResponse cartServerResponse = new CartServerResponse(10,"出现异常");
        if(user != null){
            cartServerResponse = cartService.cartSelect(user.getId(),productId);
        }
        return cartServerResponse;
    }
    @RequestMapping(value = "un_select.do",method = RequestMethod.POST)
    public @ResponseBody
    CartServerResponse cartUnSelect(int productId,HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        CartServerResponse cartServerResponse = new CartServerResponse(10,"出现异常");
        if(user != null){
            cartServerResponse = cartService.cartUnSelect(user.getId(),productId);
        }
        return cartServerResponse;
    }
    @RequestMapping(value = "select_all.do",method = RequestMethod.POST)
    public @ResponseBody
    CartServerResponse cartSelectAll(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        CartServerResponse cartServerResponse = new CartServerResponse(10,"出现异常");
        if(user != null){
            cartServerResponse = cartService.cartSelectAll(user.getId());
        }
        return cartServerResponse;
    }
    @RequestMapping(value = "un_select_all.do",method = RequestMethod.POST)
    public @ResponseBody
    CartServerResponse cartUnSelectAll(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        CartServerResponse cartServerResponse = new CartServerResponse(10,"出现异常");
        if(user != null){
            cartServerResponse = cartService.cartUnSelectAll(user.getId());
        }
        return cartServerResponse;
    }
}
