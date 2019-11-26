package com.qianfeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestController
 * @Author guoweixin
 * @Description TODO
 * @Date 2019/11/22 11:33
 * @Created by Administrator
 */
@RestController
public class TestController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("info")
    public String info(){
        System.out.println("Redis对象："+redisTemplate);
        return"info...";
    }
}
