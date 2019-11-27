package com.qianfeng.oauth;

import com.qianfeng.project.weixin.main.MenuManager;
import com.qianfeng.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/26
 * @Time:20:48
 */
@RequestMapping("oauth")
@Controller("weixinoauth11")
public class WeiXinOauth {
    @RequestMapping("weixin")       //  http://wg6k9a.natappfree.cc/oauth/weixin
    public void oauth(HttpServletResponse response) throws IOException {
        //授权后重定向的回调链接地址， 请使用 urlEncode 对链接进行处理
        String redirect_url=MenuManager.REAL_URL+"oauth/invoke";
        try {
            redirect_url = URLEncoder.encode(redirect_url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //1.用户同意授权
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" + MenuManager.appId +
                "&redirect_uri=" +redirect_url+
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=qinger" +
                "#wechat_redirect";

        response.sendRedirect(url);
    }

    /*
     用户同意授权后
     如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。
     */

    @RequestMapping("invoke")   //  oauth/invoke
    public String invoke(HttpServletRequest request){
        //2.得到code
        String code = request.getParameter("code");
        System.out.println("得到的"+code);
        String state = request.getParameter("state");
        //获取 access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" +MenuManager.appId+
                "&secret=" +MenuManager.appSecret+
                "&code=" +code+
                "&grant_type=authorization_code";

        //发送请求
        JSONObject jsonObject = WeixinUtil.httpRequest(url,"GET",null);
        System.out.println(jsonObject.toString());
        //得到access_token
        String access_token = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");
        //3.拉取用户信息
        //http：GET（请使用https协议）
        String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" +access_token+
                "&openid=" +openid+
                "&lang=zh_CN";

        JSONObject userInfoJson = WeixinUtil.httpRequest(userInfoUrl,"GET",null);
        request.setAttribute("userInfoJson",userInfoJson);

        return "oauth";
    }
}
