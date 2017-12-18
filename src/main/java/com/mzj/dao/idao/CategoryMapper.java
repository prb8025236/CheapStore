package com.mzj.dao.idao;

import com.mzj.dao.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    Integer getCurrentParentId(@Param("categoryId") Integer categoryId);

    List<Integer> getAllSonCategoryId(@Param("categoryId") Integer categoryId);

    List<Category> selectCategoryChildrenByParentId(Integer parentId);
}