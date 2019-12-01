package com.qianfeng.project.meeting.controller;

import com.qianfeng.po.MeetingGrab;
import com.qianfeng.po.MeetingPub;
import com.qianfeng.service.MeetingGrabService;
import com.qianfeng.service.MeetingPubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private MeetingGrabService meetingGrabService;

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

    /**
     * 会议抢单功能
     * 可抢单列表
     */
    @ResponseBody
    @RequestMapping("grabList")
    public List<MeetingPub> selectGrabList(@RequestParam("uid") String uid,
                                           @RequestParam("tname") String tname){
        return meetingPubService.selectGrabList(uid, tname);
    }

    /**
     * 会议   我的会议   选择讲者
     */
    @RequestMapping("chooseGrabToPage")
    public String chooseGrabToPage(@RequestParam("uid") String uid,
                                   @RequestParam("pid") String pid,
                                   HttpServletRequest request){
        request.setAttribute("uid",uid);
        request.setAttribute("pid",pid);

        return "weixin/meetingPub/grabList";
    }

    /**
     * 会议发布--》 我的会议--》选择讲者
     */
    @ResponseBody
    @RequestMapping("grabListByPid")
    public List<MeetingGrab> selectGrabListByPid(@RequestParam("pid") String pid){
        return meetingGrabService.selectGrabListByPid(pid);
    }

    /**
     * 就选你功能
     */
    @ResponseBody
    @RequestMapping("chooseGrabList")
    public int chooseGrabList(@RequestParam("pid") String pid,
                              @RequestParam("uid") String uid){
        int num = 0;
        try {
            num = meetingGrabService.chooseMeetingGrab(pid, uid);
        }catch (RuntimeException e){
            e.getMessage();
            return num;
        }

        return num;
    }
}
