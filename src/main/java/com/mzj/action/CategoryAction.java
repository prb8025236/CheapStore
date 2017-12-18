package com.mzj.action;

import com.mzj.commons.Const;
import com.mzj.commons.ServerResponse;
import com.mzj.dao.pojo.User;
import com.mzj.service.iservice.ICategoryService;
import com.mzj.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * Created by 瑞冰 on 2017/11/29.
 */
@Controller("categaryAction")
@RequestMapping("/manage/category")
public class CategoryAction {
    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(value = "get_deep_category.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Set<Integer>> getAllSonCategoryId(Integer categoryId){
        return  iCategoryService.getAllSonCategoryId(categoryId);
    }
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "add_category.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(String categoryName, int parentId, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error("用户未登录,请登录");
        }
        //校验一下是否是管理员
        if (user.getRole() == Const.Role.ROLE_ADMIN) {
            //是管理员
            //增加我们处理分类的逻辑
            return categoryService.addCategory(categoryName, parentId);

        } else {
            return ServerResponse.error("无权限操作,需要管理员权限");
        }
    }

    @RequestMapping(value="set_category_name.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse setCategoryName(String categoryName, int categoryId, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error( "用户未登录,请登录");
        }
        //校验一下是否是管理员
        if (user.getRole() == Const.Role.ROLE_ADMIN) {

            return categoryService.updateCategoryName(categoryId, categoryName);

        } else {
            return ServerResponse.error("无权限操作,需要管理员权限");
        }
    }

    @RequestMapping(value="get_category.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getChildrenCategory(Integer categoryId, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error( "用户未登录,请登录");
        }
        //校验一下是否是管理员
        if (user.getRole() == Const.Role.ROLE_ADMIN) {
            return categoryService.getChildrenCategory(categoryId);

        } else {
            return ServerResponse.error("无权限操作,需要管理员权限");
        }
    }

    @RequestMapping(value = "get_deep_category.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getCategoryAndChildrenById(Integer categoryId, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error("用户未登录,请登录");
        }
        //校验一下是否是管理员
        if (user.getRole() == Const.Role.ROLE_ADMIN) {
            return categoryService.getCategoryAndChildrenById(categoryId);
        } else {
            return ServerResponse.error("无权限操作,需要管理员权限");
        }
    }
}
