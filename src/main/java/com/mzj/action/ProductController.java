package com.mzj.action;


import com.github.pagehelper.PageInfo;
import com.mzj.commons.ServerResponse;
import com.mzj.dao.vo.ProductListVo;
import com.mzj.service.iservice.IProductService;
import com.mzj.service.iservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/11/28.
 */
@Controller
@RequestMapping("product")
@ResponseBody
public class ProductController {
    @Autowired
    @Qualifier("productSer")
    private IProductService productService;

    /**
     * 门户：根据商品id查询商品
     * @param id
     * @return
     */
    @RequestMapping(value = "detail.do", method = RequestMethod.GET)
    public ServerResponse<ProductListVo> findByProductId(int id) {

            ServerResponse<ProductListVo> data = productService.selectByPrimaryKey(id);
            return productService.selectByPrimaryKey(id);


    }

    /**
     * 门户：根据关键词查询商品(分页)
     * @param keyword
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    public ServerResponse<PageInfo<ProductListVo>> findByProductName(String keyword, Integer categoryId, Integer pageNum, Integer pageSize,String orderBy){
        ServerResponse<PageInfo<ProductListVo>> data = productService.findByProductName(keyword,categoryId,pageNum,pageSize,orderBy);
        return data;
    }



}
