package com.qianfeng.service.impl;

import com.qianfeng.mapper.WeiuserMapper;
import com.qianfeng.po.Weiuser;
import com.qianfeng.service.WeiuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/26
 * @Time:21:42
 */
@Service
public class WeiuserServiceImpl implements WeiuserService {
    @Autowired
    public WeiuserMapper weiuserMapper;

    @Override
    public Weiuser selectByOpenid(String openid) {
        return weiuserMapper.selectByOpenid(openid);
    }

    @Override
    public int insertSelective(Weiuser record) {
        return weiuserMapper.insertSelective(record);
    }
}
