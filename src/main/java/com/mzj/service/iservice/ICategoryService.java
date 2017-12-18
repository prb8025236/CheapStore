package com.mzj.service.iservice;

import com.mzj.commons.ServerResponse;
import com.mzj.dao.pojo.Category;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/11/28.
 */
public interface ICategoryService {
    /**
     * 后台：增加节点
     * @param categoryName
     * @param parentId
     * @return
     */
    ServerResponse addCategory(String categoryName, Integer parentId);

    /**
     * 后台：修改品类名字
     * @param categoryId
     * @param categoryName
     * @return
     */
    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    /**
     * 后台：获取品类子节点(平级)
     * @param categoryId
     * @return
     */
    ServerResponse<List<Category>> getChildrenCategory(Integer categoryId);

    /**
     * 后台：获取当前分类id及递归子节点categoryId
     * @param categoryId
     * @return
     */
    ServerResponse<List<Integer>> getCategoryAndChildrenById(Integer categoryId);

    Integer getCurrentParentId(Integer categoryId);

    ServerResponse<Set<Integer>> getAllSonCategoryId(Integer categoryId);

}
