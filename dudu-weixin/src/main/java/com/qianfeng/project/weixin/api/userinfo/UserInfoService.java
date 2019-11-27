package com.qianfeng.project.weixin.api.userinfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qianfeng.po.Weiuser;
import com.qianfeng.project.weixin.api.accessToken.AccessTokenRedis;
import com.qianfeng.project.weixin.util.WeixinUtil;
import com.qianfeng.service.WeiuserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/26
 * @Time:21:57
 */
@Service
public class UserInfoService {
    private static final String USER_INFO_URL="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    @Autowired
    private AccessTokenRedis accessTokenRedis;
    @Autowired
    private WeiuserService weiuserService;
        /*
		1.得到用户的openid
		2.发送get请求得到用户信息
		3.得到用户是JSON对象-->转成weiuser对象
		4.将weiuser对象添加进数据库
		*/

        /*
        收集用户信息
        先通过openid判断,如果不存在就执行添加业务
                        如果存在,要么更新要么不执行
         */

        public void userInfo(String openid){
            Weiuser weiuserObj = weiuserService.selectByOpenid(openid);
            if (weiuserObj==null){
                //1.根据用户openid向微信服务器发送get请求得到用户信息JSON对象
                JSONObject jsonObject = getJSONObjectByOpenid(openid);
                //2.得到用户是JSONObject对象-->转成Weiuser对象
                Weiuser weiuser = convertJSONObject(jsonObject);
                //3.完成微信个人信息添加到数据库表weiuser中
                int num = addWeiuser(weiuser);

            }else {
                //什么都不做
            }

        }





        public JSONObject getJSONObjectByOpenid(String openid){
            String url = USER_INFO_URL.replace("OPENID",openid).replace("ACCESS_TOKEN",accessTokenRedis.getAccessTokenVal());
            JSONObject jsonObject = WeixinUtil.httpRequest(url,"GET",null);
            return jsonObject;
        }

        public Weiuser convertJSONObject(JSONObject jsonObject){
           //1.太简单  但是会创建很多对象 Weiuser weiuser = new Weiuser();
            Weiuser weiuser = null;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                weiuser = objectMapper.readValue(jsonObject.toString(),Weiuser.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return weiuser;
        }

        public int addWeiuser(Weiuser weiuser){
            return weiuserService.insertSelective(weiuser);
        }
}
