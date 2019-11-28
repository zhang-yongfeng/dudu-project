package com.qianfeng.service;

import com.qianfeng.po.MeetingPub;

import java.util.List;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/28
 * @Time:18:55
 */
public interface MeetingPubService {
    /**
     * 微信端会议发单功能
     * @param record
     * @return
     */
    int insertWeixinSelective(MeetingPub record);

   // String selectMsxPcodeByTime(String time);

    List<MeetingPub> selectMeetingPubByUid(String uid);
}
