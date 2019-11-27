package com.qianfeng.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Weiuser implements Serializable {
    private Integer id;

    private String openid;

    private String nickname;

    private Integer sex;

    private String city;

    private String country;

    private String province;

    private String headimgurl;

    private Short subscribe;

    private String language;

    private String remark;

    /*
    补充
    "headimgurl":"http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
    "subscribe_time": 1382694957,
    "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
    "remark": "",
    "groupid": 0,
    "tagid_list":[128,2],
    "subscribe_scene": "ADD_SCENE_QR_CODE",
    "qr_scene": 98765,
    "qr_scene_str": ""
     */
    private long subscribe_time;
    private String unionid;
    private int groupid;
    private String[] tagid_list;
    private String subscribe_scene;
    private String qr_scene;
    private String qr_scene_str;

}