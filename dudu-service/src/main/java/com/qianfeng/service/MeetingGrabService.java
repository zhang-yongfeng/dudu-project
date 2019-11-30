package com.qianfeng.service;

import com.qianfeng.po.MeetingGrab;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/30
 * @Time:19:14
 */
public interface MeetingGrabService {

    /**
     * 微信端  会议抢单  --》进行抢单的功能
     */
    int insertSelectiveWeixin(MeetingGrab meetingGrab);
}
