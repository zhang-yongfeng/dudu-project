package com.qianfeng.mapper;

import com.qianfeng.po.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据wid查询user对象是否存在
     */
    @Select("select * from user where wid=#{wid}")
    User selectByWid(Integer wid);

    /**
     * 根据邮箱查询user对象信息
     */
    @Select("select * from user where email=#{email}")
    User selectByEmail(String email);

    /**
     * 执行绑定功能
     */
    @Update("update user set wid=#{wid} where email=#{email}")
    int updateByEmail(Integer wid,String email);

    /**
     * 根据weiuser表中openid查询得到user对象
     */
    @Select("SELECT * FROM `user` WHERE wid=(SELECT id FROM weiuser WHERE openid=#{openid});")
    User selectByOpenid(String openid);
}