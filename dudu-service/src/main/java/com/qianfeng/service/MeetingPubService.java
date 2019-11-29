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

    /**
     * 我的抢单者列表
     * 显示的都是发单数据的详细列表
     *
     * arg0,arg1 来传递参数
     *
     * tname=-1代表用户要查询全部类别
     * tname=java/ui/数据库
     */
    List<MeetingPub> selectGrabList(String uid,String tname);
}
