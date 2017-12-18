package com.mzj.action;


import com.github.pagehelper.PageInfo;
import com.mzj.commons.Const;
import com.mzj.commons.ResponseCode;
import com.mzj.commons.ServerResponse;
import com.mzj.dao.pojo.Product;
import com.mzj.dao.pojo.User;
import com.mzj.dao.vo.*;
import com.mzj.service.iservice.IProductService;
import com.mzj.service.iservice.ProductService;
import com.mzj.utils.FTPUtil;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 */
@Controller
@RequestMapping("/manage/product")
public class ProductManagerAction {
    @Autowired
    @Qualifier("productSer")
    private IProductService productService;

    /**
     * 后台： 商品上下架
     *
     * @param productId
     * @param status
     * @return
     */
    @RequestMapping(value = "set_sale_status.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> updateProductStatus(Integer productId, Integer status) {
        ServerResponse<String> data = productService.updateProductStatus(productId, status);
        return data;
    }

    /**
     * 后台： 根据商品id查询商品
     *
     * @param productId
     * @param session
     * @return
     */
    @RequestMapping(value = "detail.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<ProductDetailVo> selectByProductId(Integer productId, HttpSession session) {

        session.setAttribute("productId", productId);
        return productService.selectByProductId(productId);
    }

    /**
     * 后台：根据关键词查询商品(分页)
     *
     * @param productName
     * @param productId
     * @return
     */
    @RequestMapping(value = "search.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo<ProductSearchVo>> searchProduct(String productName, Integer productId, Integer pageNum, Integer pageSize, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return productService.searchProduct(productName, productId, pageNum, pageSize);
        }
        return ServerResponse.error("用户未登录,请登录");
    }

    /**
     * 后台：更新或插入产品
     *
     * @param product
     * @return
     */
    @RequestMapping(value = "save.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Product> saveOrUpdateProduct(Product product) {
        ServerResponse<Product> data = productService.saveOrUpdateProduct(product);
        return data;
    }


    /**
     * 后台：查询全部商品
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo<ProductListVo>> findAllProduct(Integer pageNum, Integer pageSize, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return productService.findAllProduct(pageNum, pageSize);
        }
        return ServerResponse.error("用户未登录,请登录");
    }


    //实现文件上传功能
    @RequestMapping(value = "upload.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<ProductUploadVo> doFlieUpload(@RequestParam("upload_file") MultipartFile sourceFile, HttpSession session) throws Exception {
        Integer productId = (Integer) session.getAttribute("productId");
        String fileUri = "";
        if (!sourceFile.isEmpty()) {
            try {
                ClientGlobal.init("C:\\Users\\瑞冰\\Desktop\\mmall\\src\\main\\resources\\client.conf");
                // 3、创建一个TrackerClient对象。
                TrackerClient trackerClient = new TrackerClient();
                // 4、创建一个TrackerServer对象。
                TrackerServer trackerServer = trackerClient.getConnection();
                // 5、声明一个StorageServer对象，null。
                StorageServer storageServer = null;
                // 6、获得StorageClient对象。
                StorageClient storageClient = new StorageClient(trackerServer, storageServer);
                // 7、直接调用StorageClient对象方法上传文件即可。
                String[] strings = storageClient.upload_file(sourceFile.getBytes(), "jpg", null);
                String url = "";
                for (String str : strings) {
                    url += str + "/";
                }
                fileUri = "http://192.168.47.128/" + url.substring(0, url.lastIndexOf("/"));
                System.out.println(fileUri);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return productService.updateImage(productId, fileUri);
    }



    //实现富文本上传图片
    @RequestMapping(value="richtext_img_upload.do",method=RequestMethod.POST)
    @ResponseBody
    public ProductRichTextVo doRichFlieUpload(@RequestParam("upload_file")MultipartFile sourceFile, HttpSession session) throws Exception{
        ProductRichTextVo productRichTextVo = new ProductRichTextVo();
        if(!sourceFile.isEmpty()){
            File targetFile = new File("D:/richtemp",
                    System.currentTimeMillis()+sourceFile.getOriginalFilename());
            FileUtils.copyInputStreamToFile(sourceFile.getInputStream(),targetFile );
            productRichTextVo.setFile_path("http://img.emall.com/"+targetFile.getName());
            productRichTextVo.setMsg("上传成功");
            productRichTextVo.setSuccess("true");
            if(productRichTextVo != null){

                return productRichTextVo;
            }else{
                productRichTextVo.setFile_path("http://img.emall.com/"+targetFile.getName());
                productRichTextVo.setMsg("error message");
                productRichTextVo.setSuccess("false");
            }
        }
        return productRichTextVo;
    }

}
