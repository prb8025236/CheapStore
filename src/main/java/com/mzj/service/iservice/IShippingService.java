package com.mzj.service.iservice;


import com.github.pagehelper.PageInfo;
import com.mzj.commons.ServerResponse;
import com.mzj.dao.pojo.Shipping;

/**
 * Created by 瑞冰 on 2017/11/29.
 */
public interface IShippingService {

    /**
     * 添加地址
     * @param shipping
     * @return
     */
    public ServerResponse<Integer> addShipping(Shipping shipping);

    /**
     * 删除地址
     * @param shippingId
     */
    public ServerResponse<String> delShipping(int shippingId);

    /**
     * 登录状态更新地址
     * @param shipping
     */
    public ServerResponse<String> updateShipping(Shipping shipping);

    /**
     * 查看选中的具体的地址
     * @param shippingId
     * @return
     */
    public ServerResponse<Shipping> selectShipping(Integer userId, int shippingId);

    /**
     * 查看地址列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo<Shipping>> listShipping(Integer userId, Integer pageNum, Integer pageSize);
}
