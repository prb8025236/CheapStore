package com.mzj.dao.idao;

import com.mzj.dao.pojo.Product;
import com.mzj.dao.vo.ProductDetailVo;
import com.mzj.dao.vo.ProductListVo;
import com.mzj.dao.vo.ProductSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);
    ProductListVo selectByPKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    List<Product> selectByNameAndProductId(@Param("productName")String productName, @Param("productId") Integer productId);
    /**
     * 自定义方法：前台根据关键词查询商品
     * @param keyword
     * @param categoryId
     * @return
     */
    List<ProductListVo> findByProductName(@Param("keyword") String keyword, @Param("categoryId")Integer categoryId);

    /**
     * 自定义方法：后台商品上下架
     * @param productId
     * @param status
     * @return
     */
    int updateProductStatus(@Param("productId") Integer productId, @Param("status") Integer status);

    /**
     * 后台:商品详情
     * @param productId
     * @return
     */
    ProductDetailVo selectByProductId(@Param("productId") Integer productId);


    /**
     * 后台：商品搜索
     * @param productName
     * @param productId
     * @return
     */
    List<ProductSearchVo> searchProduct(@Param("productName")String productName, @Param("productId")Integer productId);


    /**
     * 后台：更新或新增产品
     * @param product
     * @return
     * @throws Exception
     */
    int saveOrUpdateProduct(Product product);

    /**
     * 后台：查询所有
     * @return
     */
    List<ProductListVo> findAll();

    /**
     * 后台：修改图片地址
     * @param productId
     * @param uri
     * @return
     */
    int updateImage(@Param("productId") Integer productId, @Param("uri") String uri);
}