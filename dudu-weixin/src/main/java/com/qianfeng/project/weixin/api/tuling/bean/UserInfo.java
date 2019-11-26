package com.qianfeng.project.weixin.api.tuling.bean;

import lombok.Data;

/**
 * @Classname UserInfo
 * @Author guoweixin
 * @Description TODO
 * @Date 2019/11/21 9:56
 * @Created by Administrator
 */
@Data
public class UserInfo {
    /** 机器人标识*/
    private String apiKey;
    /** 用户唯一标识*/
    private String userId;
}
