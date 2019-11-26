package com.qianfeng.project.weixin.api.accessToken;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Classname AccessTokenListener
 * @Author guoweixin
 * @Description TODO
 * @Date 2019/11/22 10:53
 * @Created by Administrator
 */
@WebListener
public class AccessTokenListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(".....项目启动了.....");
       // new AccessTokenThread().start();
    }

     @Override
     public void contextDestroyed(ServletContextEvent sce) {
    }
}
