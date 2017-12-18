package com.mzj.action;

import com.mzj.commons.ServerResponse;
import com.mzj.dao.pojo.User;
import com.mzj.service.iservice.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/11/28.
 */
@Controller
@RequestMapping("order")
public class OrderAction {

    @Autowired
    @Qualifier("orderSer")
    private IOrderService iOrderService;

    @RequestMapping(value = "create.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse create(HttpSession session, Integer shippingid) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.error("请先登录");
        }
        return iOrderService.create(user.getId(), shippingid);
    }

    @RequestMapping("cancel.do")
    @ResponseBody
    public ServerResponse cancel(HttpSession session, Long orderNo) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.error("请先登录");
        }
        return iOrderService.cancel(user.getId(), orderNo);
    }

    @RequestMapping("get_order_cart_product.do")
    @ResponseBody
    public ServerResponse getOrderCartProduct(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.error("请先登录");
        }
        return iOrderService.getOrderCartProduct(user.getId());
    }


    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse detail(HttpSession session, Long orderNo) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.error("请先登录");
        }
        return iOrderService.getOrderDetail(user.getId(), orderNo);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse list(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
     //   User user = (User) session.getAttribute("user");
//        if (user == null) {
//            return ServerResponse.error("请先登录");
//        }
        User user = new User();
        user.setId(1);
        return iOrderService.getOrderList(user.getId(), pageNum, pageSize);
    }


}
