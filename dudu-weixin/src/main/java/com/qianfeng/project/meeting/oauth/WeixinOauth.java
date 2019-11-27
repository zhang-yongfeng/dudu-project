package com.qianfeng.project.meeting.oauth;

import com.qianfeng.po.User;
import com.qianfeng.po.Weiuser;
import com.qianfeng.project.weixin.main.MenuManager;
import com.qianfeng.project.weixin.util.WeixinUtil;
import com.qianfeng.service.UserService;
import com.qianfeng.service.WeiuserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Date: 2019/11/27
 * @Time:16:24
 */
@RequestMapping("oauth")
@Controller
public class WeixinOauth {
    @Autowired
    private WeiuserService weiuserService;
    @Autowired
    private UserService userService;

    @RequestMapping("weixin/user")       //  http://wg6k9a.natappfree.cc/oauth/weixin/user
    public void oauth(HttpServletResponse response) throws IOException {
        //授权后重定向的回调链接地址， 请使用 urlEncode 对链接进行处理
        String redirect_url= MenuManager.REAL_URL+"oauth/weixin/user/invoke";
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
                "&scope=snsapi_base" +
                "&state=qinger" +
                "#wechat_redirect";

        response.sendRedirect(url);
    }

    /*
     用户同意授权后
     如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。
     */

    @RequestMapping("weixin/user/invoke")   //  oauth/weixin/user/invoke
    public String invoke(HttpServletRequest request) {
        //2.得到code
        String code = request.getParameter("code");
        //获取 access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + MenuManager.appId +
                "&secret=" + MenuManager.appSecret +
                "&code=" + code +
                "&grant_type=authorization_code";

        //发送请求
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);
        System.out.println(jsonObject.toString());
//        //得到access_token
//        String access_token = jsonObject.getString("access_token");
        //得到openid
        String openid = jsonObject.getString("openid");
        /**
         * 1.根据openid查询weiuser表   得到主键id
         * 2.根据wid去查询user表
         */
        Weiuser weiuser = weiuserService.selectByOpenid(openid);
        if (weiuser == null) {
            //微信个人信息不存在
            //1.提示操作异常,请重新关注
            //2.收集用户信息,存入数据库
            System.out.println("该用户信息不存在");
        }else {
            User user = userService.selectByWid(weiuser.getId());
            if (user == null) {
                request.setAttribute("wid",weiuser.getId());
                //未绑定   跳到登录页面
                return "weixin/login";
            } else {
                //已绑定   跳到目标页面
                request.setAttribute("user",user);
                return "weixin/user/userInfo";
            }
        }

//        //3.拉取用户信息
//        //http：GET（请使用https协议）
//        String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?" +
//                "access_token=" +access_token+
//                "&openid=" +openid+
//                "&lang=zh_CN";
//
//        JSONObject userInfoJson = WeixinUtil.httpRequest(userInfoUrl,"GET",null);
//        request.setAttribute("userInfoJson",userInfoJson);

        return "oauth";
    }
}
