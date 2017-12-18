package com.mzj.service.iservice;


import com.github.pagehelper.PageInfo;
import com.mzj.commons.ServerResponse;
import com.mzj.dao.pojo.Product;
import com.mzj.dao.vo.ProductDetailVo;
import com.mzj.dao.vo.ProductListVo;
import com.mzj.dao.vo.ProductSearchVo;
import com.mzj.dao.vo.ProductUploadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Administrator on 2017/11/28.
 */

public interface ProductService {



    //门户：根据商品id查询商品
    public ServerResponse<ProductListVo> selectByPrimaryKey(Integer id);

    //门户：根据关键词查询商品
    public  ServerResponse<PageInfo<ProductListVo>> findByProductName(String keyword, Integer categoryId, Integer pageNum, Integer pageSize, String orderBy);

    //后台： 商品上下架
    public ServerResponse<String> updateProductStatus(Integer productId, Integer status);

    //后台： 根据商品id查询商品
    public ServerResponse<ProductDetailVo> selectByProductId(Integer productId);

    //后台：根据关键词查询商品
    public  ServerResponse<PageInfo<ProductSearchVo>> searchProduct(String productName, Integer productId, Integer pageNum, Integer pageSize);

    //后台： 新增商品或更新商品
    public ServerResponse<Product> saveOrUpdateProduct(Product product);

    //后台：查询所有商品
    public ServerResponse<PageInfo<ProductListVo>> findAllProduct(Integer pageNum, Integer pageSize);

    //后台：图片修改
    public ServerResponse<ProductUploadVo> updateImage(Integer productId, String uri);
}
