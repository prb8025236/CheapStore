package com.mzj.action;


import com.github.pagehelper.PageInfo;
import com.mzj.commons.Const;
import com.mzj.commons.ServerResponse;
import com.mzj.dao.pojo.Shipping;
import com.mzj.dao.pojo.User;
import com.mzj.service.iservice.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by 瑞冰 on 2017/11/29.
 */
@Controller("shippingAction")
@RequestMapping("/shipping/")
public class ShippingAction {

    @Autowired
    @Qualifier("shippingService")
    private IShippingService iShippingService;

    @RequestMapping(value = "add.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Integer> addShipping(Shipping shipping){
        return iShippingService.addShipping(shipping);
    }
    @RequestMapping(value = "del.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delShipping(int shippingId){
        return iShippingService.delShipping(shippingId);
    }
    @RequestMapping(value = "update.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> updateShipping(Shipping shipping){
        return iShippingService.updateShipping(shipping);
    }
    @RequestMapping(value = "select.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Shipping> selectShipping(Integer shippingId,HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user != null) {
            return iShippingService.selectShipping(user.getId(), shippingId);
        }
        return ServerResponse.error("请登陆之后查询");
    }
    @RequestMapping(value = "list.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo<Shipping>> listShipping(Integer pageNum, Integer pageSize,HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user != null) {
            return iShippingService.listShipping(user.getId(),pageNum,pageSize);
        }
        return ServerResponse.error("请登陆之后查询");
    }
}
