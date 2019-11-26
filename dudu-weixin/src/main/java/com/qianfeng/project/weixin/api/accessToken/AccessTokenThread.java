package com.qianfeng.project.weixin.api.accessToken;

import com.qianfeng.project.weixin.main.MenuManager;
import com.qianfeng.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;

/**
 * @Classname AccessTokenThread
 * @Author guoweixin
 * @Description TODO  线程定时向微信服务器获得access_token
 * @Date 2019/11/22 10:42
 * @Created by Administrator
 */
public class AccessTokenThread  extends Thread {
    /** 得到acccess_token数值*/
    public static String access_token_val;

    @Override
    public void run() {
        while(true){
            //  7200秒   1秒=1000  ==>7200*1000
            try {
                access_token_val=getAccessTokenVal();
                System.out.println("得到的："+access_token_val);
                Thread.sleep(7000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 向微信服务器发送get请求，得到access_token
     * @return
     */
    private String getAccessTokenVal(){
        String requestUrl=ACCESS_TOKEN_URL.replace("APPID", MenuManager.appId).replace("APPSECRET",MenuManager.appSecret);
        JSONObject jsonObject=WeixinUtil.httpRequest(requestUrl,"GET",null);
       String result= (String) jsonObject.get("access_token");
        return result;
    }
}
