package com.mzj.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mzj.commons.ServerResponse;
import com.mzj.dao.idao.CategoryMapper;
import com.mzj.dao.pojo.Category;
import com.mzj.service.iservice.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/11/28.
 */
@Service("CategoryService")
public class CategoryServiceImpl implements ICategoryService {
    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional
    @Override
    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.error("添加类别参数错误");
        }

        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);
        int row = categoryMapper.insert(category);
        if (row > 0) {
            return ServerResponse.successMsg("添加类别成功");

        }
        return ServerResponse.error("添加类别失败");
    }

    @Transactional
    @Override
    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        if (categoryId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.error("更新类别参数错误");
        }

        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        int row = categoryMapper.updateByPrimaryKeySelective(category);
        if (row > 0) {
            return ServerResponse.successMsg("修改类别成功");
        }
        return ServerResponse.error("修改类别失败");
    }

    //查询本节点的子节点
    @Transactional
    @Override
    public ServerResponse<List<Category>> getChildrenCategory(Integer categoryId) {

        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if (categoryList != null && categoryList.size() > 0) {
            return ServerResponse.success(categoryList);
        }
        return ServerResponse.error("未找到当前类别的子类别");
    }



    //递归查询本节点的id及孩子节点的id
    @Transactional
    @Override
    public ServerResponse<List<Integer>> getCategoryAndChildrenById(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        List<Integer> categoryIdList=Lists.newArrayList();
        if (categoryId != null) {
            findChildCategory(categorySet, categoryId);
            for (Category categoryItem : categorySet) {
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.success(categoryIdList);
    }

    //递归算法,算出子节点
    private Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for (Category categoryItem : categoryList) {
            findChildCategory(categorySet, categoryItem.getId());
        }
        return categorySet;
    }
    @Override
    @Transactional(readOnly = true)
    public Integer getCurrentParentId(Integer categoryId) {
        Integer id = -1;
        while (true){
            id = categoryMapper.getCurrentParentId(categoryId);
            if(id == 0){
                id = categoryId;
                break;
            }else {
                categoryId = id;
            }
        }
        return  id;
    }
    @Override
    @Transactional(readOnly = true)
    public ServerResponse<Set<Integer>> getAllSonCategoryId(Integer categoryId) {
        Set<Integer> categorySet = new HashSet<>();
        categorySet = getAllChildCategoryId(getCurrentParentId(categoryId));
        categorySet.add(getCurrentParentId(categoryId));
        return ServerResponse.success(categorySet);
    }
    private Set<Integer> getAllChildCategoryId(Integer categoryId){
        Set<Integer> categorySet = new HashSet<>();
        if(categoryMapper.getAllSonCategoryId(categoryId) != null){
            categorySet.addAll(categoryMapper.getAllSonCategoryId(categoryId));
            return categorySet;
        }
        for (Integer i:categorySet){
            return getAllChildCategoryId(i);
        }
        return categorySet;
    }
}
