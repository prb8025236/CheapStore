package com.mzj.dao.idao;

import com.mzj.dao.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order selectByUserIdAndOrderNo(@Param("userid") Integer userid, @Param("orderNo") Long orderNo);

    List<Order> selectByUserId(Integer userId);

    List<Order> selectAllOrder();

}