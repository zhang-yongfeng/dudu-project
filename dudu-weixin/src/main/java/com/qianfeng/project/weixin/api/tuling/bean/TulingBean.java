package com.qianfeng.project.weixin.api.tuling.bean;

import lombok.Data;

/**
 * @Classname TulingBean
 * @Author guoweixin
 * @Description TODO
 * @Date 2019/11/21 9:53
 * @Created by Administrator
 */
@Data
public class TulingBean {
    private int reqType=0;//输入类型:0-文本(默认)、1-图片、2-音频

    private  Perception perception;

    private UserInfo userInfo;

}
