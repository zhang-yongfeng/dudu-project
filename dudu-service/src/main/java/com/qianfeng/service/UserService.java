package com.qianfeng.service;

import com.qianfeng.po.User;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/27
 * @Time:16:43
 */
public interface UserService {

    // ######TODO 后台端
    /**
     * 后台端   增删改查常用方法
     */

   // ######TODO 微信端
    /**
     * 微信端
     * @param wid
     * @return
     */
    User selectByWid(Integer wid);

    User selectByEmail(String email);

    int updateByEmail(Integer wid,String email);
}
