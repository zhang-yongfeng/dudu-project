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
    int updateByPrimaryKeySelective(User record);
   // ######TODO 微信端
    /**
     * 微信端
     * @param wid
     * @return
     */
    //wid查询user对象
    User selectByWid(Integer wid);
    //根据邮箱查询user对象信息
    User selectByEmail(String email);
    //进行登陆绑定功能
    int updateByEmail(Integer wid,String email);
    //会议发单的角色判断
    User selectByOpenid(String openid);
}
