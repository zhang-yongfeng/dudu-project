package com.qianfeng.project.weixin.api.accessToken;

import com.qianfeng.project.weixin.main.MenuManager;
import com.qianfeng.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Classname AccessTokenRedis
 * @Author guoweixin
 * @Description TODO
 * @Date 2019/11/22 11:36
 * @Created by Administrator
 */
@Service
public class AccessTokenRedis {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name="redisTemplate")
    private ValueOperations<String, String> string;

    private static final String REDIS_KEY_ACCESS_TOKEN="access_token";
    /**
     * key如果存在，到Redis中进行查询
     * 如果不存在，在微信服务器中获得请求结果。存入Redis中
     * key: access_token
     * @return
     */
    public String getAccessTokenVal(){
        if(redisTemplate.hasKey(REDIS_KEY_ACCESS_TOKEN)){
          //  System.out.println("在Redis中查询的accessToken:");
          String result=string.get(REDIS_KEY_ACCESS_TOKEN);
          // return (String) redisTemplate.opsForValue().get(REDIS_KEY_ACCESS_TOKEN);
            return  result;
        }
        else{
            String accessTokenVal=getWeixinAccessTokenVal();
           // System.out.println("在微信服务器中查询的accessToken"+accessTokenVal);

            string.set(REDIS_KEY_ACCESS_TOKEN,accessTokenVal,2, TimeUnit.HOURS);
            return accessTokenVal;
        }
    }


    private static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 向微信服务器发送get请求，得到access_token
     * @return
     */
    private String getWeixinAccessTokenVal(){
        String requestUrl=ACCESS_TOKEN_URL.replace("APPID", MenuManager.appId).replace("APPSECRET",MenuManager.appSecret);
        JSONObject jsonObject= WeixinUtil.httpRequest(requestUrl,"GET",null);
        String result= (String) jsonObject.get("access_token");
        return result;
    }
}
