package com.mzj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mzj.commons.ResponseCode;
import com.mzj.commons.ServerResponse;
import com.mzj.dao.idao.ProductMapper;
import com.mzj.dao.pojo.Category;
import com.mzj.dao.pojo.Product;
import com.mzj.dao.vo.ProductDetailVo;
import com.mzj.dao.vo.ProductListVo;
import com.mzj.dao.vo.ProductSearchVo;
import com.mzj.dao.vo.ProductUploadVo;
import com.mzj.service.iservice.IProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */
@Service("productSer")
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;


    @Override
    public ServerResponse<String> setSaleStatus(Integer productId,Integer status){
        if(productId == null || status == null){
            return ServerResponse.error(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if(rowCount > 0){
            return ServerResponse.successMsg("修改产品销售状态成功");
        }
        return ServerResponse.error("修改产品销售状态失败");
    }



    @Override
    public ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(productName)){
            productName = new StringBuilder().append("%").append(productName).append("%").toString();
        }
        List<Product> productList = productMapper.selectByNameAndProductId(productName,productId);
        List<ProductListVo> productListVoList = Lists.newArrayList();
        for(Product productItem : productList){
            ProductListVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }
        PageInfo pageResult = new PageInfo(productList);
        pageResult.setList(productListVoList);
        return ServerResponse.success(pageResult);
    }
    private ProductListVo assembleProductListVo(Product product){
        ProductListVo productListVo = new ProductListVo();
        productListVo.setId(product.getId());
        productListVo.setName(product.getName());
        productListVo.setCategoryId(product.getCategoryId());
        productListVo.setImageHost("http://img.happymmall.com/");
        productListVo.setMainImage(product.getMainImage());
        productListVo.setPrice(product.getPrice());
        productListVo.setSubtitle(product.getSubtitle());
        productListVo.setStatus(product.getStatus());
        return productListVo;
    }

    /**
     * 门户：根据商品id查询商品
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public ServerResponse<ProductListVo> selectByPrimaryKey(Integer id) {
        ProductListVo product = null;
        try {
            product = productMapper.selectByPKey(id);
            if(product != null){
                return ServerResponse.success(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ServerResponse.error("该商品已下架或删除");


    }

    /**
     * 门户：根据关键词查询商品(分页)
     * @param keyword
     * @param categoryId
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public ServerResponse<PageInfo<ProductListVo>> findByProductName(String keyword, Integer categoryId, Integer pageNum, Integer pageSize,String orderBy) {
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum, pageSize,orderBy);
        List<ProductListVo>  list = productMapper.findByProductName(keyword,categoryId);
        if(list.size() > 0){
            //用PageInfo对结果进行包装
            PageInfo page = new PageInfo(list);
            return ServerResponse.success(page);
        }else {
            return ServerResponse.error("参数错误");
        }

    }

    /**
     * 后台： 商品上下架
     * @param productId
     * @param status
     * @return
     */
    @Transactional
    @Override
    public ServerResponse<String> updateProductStatus(Integer productId, Integer status) {
        int result = productMapper.updateProductStatus(productId,status);
        if(result == 1){
            return ServerResponse.successMsg("修改商品状态成功");
        }else {
            return ServerResponse.error("修改商品状态失败");
        }

    }

    /**
     *后台：根据商品id查询商品
     * @param productId
     * @return
     */
    @Transactional
    @Override
    public ServerResponse<ProductDetailVo> selectByProductId(Integer productId){
        ProductDetailVo productDetailVo =  null;
        try {
            productDetailVo = productMapper.selectByProductId(productId);
            if(productDetailVo != null){
                productDetailVo.setImageHost("http://img.emall.com/");
                return ServerResponse.success(productDetailVo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ServerResponse.error("没有权限");
    }


    /**
     *后台：根据关键词查询商品
     * @param productName
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Transactional
    @Override
    public ServerResponse<PageInfo<ProductSearchVo>> searchProduct(String productName, Integer productId, Integer pageNum, Integer pageSize) {
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        List<ProductSearchVo> list = productMapper.searchProduct(productName,productId);
        if(list.size() > 0){
            //用PageInfo对结果进行包装
            PageInfo page = new PageInfo(list);
            return ServerResponse.success(page);
        }else {
            return ServerResponse.error("用户未登录,请登录");
        }
    }

    /**
     * 后台：更新或插入产品
     * @param product
     * @return
     */
    @Transactional
    @Override
    public ServerResponse<Product> saveOrUpdateProduct(Product product) {
        try {
            int result = productMapper.saveOrUpdateProduct(product);
            if(result == 1){
                if(product.getId() != null){
                    return ServerResponse.successMsg("更新商品成功");
                }else{
                    return ServerResponse.successMsg("新增商品成功");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.error("更新商品失败");
    }

    /**
     * 后台：查询全部商品
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Transactional
    @Override
    public ServerResponse<PageInfo<ProductListVo>> findAllProduct(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductListVo>  list = productMapper.findAll();
        if(list.size() > 0){
            //用PageInfo对结果进行包装
            PageInfo page = new PageInfo(list);
            return ServerResponse.success(page);
        }else {
            return ServerResponse.error("商品不存在");
        }
    }

    /**
     * 后台：修改图片地址
     * @param productId
     * @param uri
     * @return
     */
    @Transactional
    @Override
    public ServerResponse<ProductUploadVo> updateImage(Integer productId, String uri){
        int result = productMapper.updateImage(productId,uri);
        if(result == 1){
            ProductUploadVo productUploadVo = new ProductUploadVo(uri);
            productUploadVo.setUrl("http://img.emall.com/"+productUploadVo .getUri());
            productUploadVo.setUri(productUploadVo .getUri());
            return ServerResponse.success(productUploadVo);
        }
        return ServerResponse.error("图片上传失败");
    }
}
