package com.mzj.service.iservice;

import com.mzj.commons.ServerResponse;
import com.mzj.dao.pojo.User;

/**
 * Created by Administrator on 2017/11/29.
 */
public interface IUserService {
    public ServerResponse login(String username, String password);
    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str,String type);

    ServerResponse selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username,String question,String answer);

    ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInformation(Integer userId);

    ServerResponse checkAdminRole(User user);
}
