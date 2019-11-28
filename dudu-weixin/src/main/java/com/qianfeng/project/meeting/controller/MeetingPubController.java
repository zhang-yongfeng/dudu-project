package com.qianfeng.project.meeting.controller;

import com.qianfeng.po.MeetingPub;
import com.qianfeng.service.MeetingPubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/28
 * @Time:19:05
 */
@Controller
@RequestMapping("meetingPub")
public class MeetingPubController {
    @Autowired
    private MeetingPubService meetingPubService;

    /**
     * 会议添加
     * @param meetingPub
     * @return
     */
    @ResponseBody
    @RequestMapping("add")
    public int meetingPubAdd(MeetingPub meetingPub){

        int num = meetingPubService.insertWeixinSelective(meetingPub);
        return num;
    }

    /**
     * 我的会议列表
     */
    @ResponseBody
    @RequestMapping("byUid")
    public List<MeetingPub> selectMyMeetingPub(@RequestParam("uid") String uid){

        return meetingPubService.selectMeetingPubByUid(uid);
    }
}
