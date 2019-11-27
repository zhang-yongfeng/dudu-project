package com.qianfeng.service.impl;

import com.qianfeng.mapper.UserMapper;
import com.qianfeng.po.User;
import com.qianfeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/27
 * @Time:16:45
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByWid(Integer wid) {
        return userMapper.selectByWid(wid);
    }

    @Override
    public User selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public int updateByEmail(Integer wid, String email) {
        return userMapper.updateByEmail(wid,email);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }
}
