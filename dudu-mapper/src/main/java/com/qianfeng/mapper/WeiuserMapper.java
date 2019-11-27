package com.qianfeng.mapper;


import com.qianfeng.po.Weiuser;
import org.apache.ibatis.annotations.Select;

public interface WeiuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Weiuser record);

    int insertSelective(Weiuser record);

    Weiuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weiuser record);

    int updateByPrimaryKey(Weiuser record);


    //根据openid判断weiuser是否存在对象信息
    @Select("select * from weiuser where openid=#{openid}")
    Weiuser selectByOpenid(String openid);
}