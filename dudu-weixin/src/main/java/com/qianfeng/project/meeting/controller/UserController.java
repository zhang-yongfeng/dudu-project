package com.qianfeng.project.meeting.controller;

import com.qianfeng.po.User;
import com.qianfeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/27
 * @Time:16:26
 */
@RequestMapping("user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 登陆(绑定)功能
     */
    @RequestMapping("login")
    @ResponseBody
    public String login(@RequestParam("email") final String email,
                        @RequestParam("wid") final Integer wid){
        //1.用户输入的邮箱在数据库user表中是否存在
        User user = userService.selectByEmail(email);
        if (user!=null){
            if (user.getWid()!=null){
                return "1";//该邮箱已被其他微信用户绑定,如有疑问,请联系管理员
            }else {
                //进行绑定功能的业务逻辑,修改user表中的wid值
                userService.updateByEmail(wid,email);
                return "2";//登陆成功
            }
        }else {
            return "3";//该用户的邮箱不存在,无法进行登陆功能
        }
    }

    /**
     * 更新用户信息
     */

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value = "updateUser")
    public String updateUserInfo(User user){
        int num = userService.updateByPrimaryKeySelective(user);

        return num+"";
    }

    /**
     * 页面跳转   Controller操作
     * 登录页面
     */
    @RequestMapping("to/login")
    public String login(HttpServletRequest request){
        String wid = request.getParameter("wid");
        request.setAttribute("wid",wid);
        return "weixin/login";
    }

    /**
     * 无权页面
     */
    @RequestMapping("to/unauth")
    public String unauth(){
        return "weixin/unauth";
    }

    /**
     * 进入抢单页面
     */
    @RequestMapping("to/meetingGrab")
    public String meetingGrab(HttpServletRequest request){
        String uid = request.getParameter("uid");
        request.setAttribute("uid",uid);
        return "weixin/meetingGrab/meetingGrab";
    }
}
