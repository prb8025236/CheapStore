package com.mzj.dao.idao;

import com.mzj.dao.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    Shipping selectByPrimaryKey(@Param("userId") Integer userId,@Param("id") Integer id);
    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);
    List<Shipping> selectAllByUserId(@Param("userId") Integer userId);
}