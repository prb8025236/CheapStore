package com.mzj.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mzj.commons.ServerResponse;
import com.mzj.dao.idao.ShippingMapper;
import com.mzj.dao.pojo.Shipping;
import com.mzj.service.iservice.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 瑞冰 on 2017/11/29.
 */
@Service("shippingService")
public class ShippingServiceImpl implements IShippingService {
    @Autowired
    private ShippingMapper shippingMapper;
    @Override
    @Transactional
    public ServerResponse<Integer> addShipping(Shipping shipping) {
        int count = shippingMapper.insert(shipping);
        if(count == 0) {
            return ServerResponse.error("新建地址失败");
        }
        return ServerResponse.success("新建地址成功", shipping.getId());
    }

    @Override
    @Transactional
    public ServerResponse<String> delShipping(int shippingId) {
        int count = shippingMapper.deleteByPrimaryKey(shippingId);
        if(count == 0){
            return ServerResponse.error("删除地址失败");
        }
        return ServerResponse.success("删除地址成功");
    }

    @Override
    @Transactional
    public ServerResponse<String> updateShipping(Shipping shipping) {
        int count = shippingMapper.updateByPrimaryKeySelective(shipping);
        if(count == 0){
            return ServerResponse.error("更新地址失败");
        }
        return ServerResponse.success("更新地址成功");
    }

    @Override
    @Transactional(readOnly = true)
    public ServerResponse<Shipping> selectShipping(Integer userId,int shippingId) {
        Shipping shipping = shippingMapper.selectByPrimaryKey(userId,shippingId);
        return ServerResponse.success(shipping);
    }

    @Override
    @Transactional(readOnly = true)
    public ServerResponse<PageInfo<Shipping>> listShipping(Integer userId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> list = shippingMapper.selectAllByUserId(userId);
        PageInfo<Shipping> page = new PageInfo(list);
        return ServerResponse.success(page);
    }
}
