package com.mzj.service.impl;

import com.mzj.dao.idao.UserMapper;
import com.mzj.dao.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/11/27.
 */
public class TSer {
    ApplicationContext cx;
    UserMapper um;

    @Before
    public void af() {
        cx = new ClassPathXmlApplicationContext("applicationContext.xml");
        um = (UserMapper) cx.getBean("userMapper");
        System.out.println(um);
    }
    @Test
    public void save() {

        User u = new User();
        u.setId(2);
        u.setPassword("111");
        u.setRole(1);
        u.setUsername("222");
        u.setAnswer("ss");
        u.setEmail("qqq");
        u.setPhone("222");
        u.setQuestion("xxx");
        um.insert(u);
    }
}
