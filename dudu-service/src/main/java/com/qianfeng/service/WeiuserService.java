package com.qianfeng.service;

import com.qianfeng.po.Weiuser;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/26
 * @Time:21:42
 */
public interface WeiuserService {

    //根据openid判断weiuser是否存在对象信息
    Weiuser selectByOpenid(String openid);

    int insertSelective(Weiuser record);
}
