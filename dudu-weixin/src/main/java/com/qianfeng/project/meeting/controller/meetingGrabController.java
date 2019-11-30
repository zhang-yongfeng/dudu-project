package com.qianfeng.project.meeting.controller;

import com.qianfeng.po.MeetingGrab;
import com.qianfeng.po.MeetingPub;
import com.qianfeng.service.MeetingGrabService;
import com.qianfeng.service.MeetingPubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/30
 * @Time:16:22
 */
@Controller
public class meetingGrabController {
    @Autowired
    private MeetingGrabService meetingGrabService;
    @Autowired
    private MeetingPubService meetingPubService;

    /**
     * 会议抢单--》可抢单--》目标页面
     */
    @RequestMapping("meetingGrab/addPage")
    public String meetingGrabAddPage(@RequestParam("uid")String uid,
                                     @RequestParam("pid")String pid,
                                     Map<String,Object> map
    ){
        map.put("uid",uid);
        map.put("pid",pid);
        return "weixin/meetingGrab/meetingGrabAdd";
    }

    /**
     * 进行  会议抢单添加功能
     * 并进入  会议抢单列表页面
     */
    @RequestMapping("meetingGrab/add")
    public ModelAndView meetingGrabAdd(MeetingGrab meetingGrab){
        ModelAndView modelAndView = new ModelAndView();

        meetingGrabService.insertSelectiveWeixin(meetingGrab);

        modelAndView.addObject("uid",meetingGrab.getUid());

        //"weixin/meetingGrab/meetingGrab";
        modelAndView.setViewName("weixin/meetingGrab/meetingGrab");
        return modelAndView;
    }

    @RequestMapping("meetingGrab/uid")
    @ResponseBody
    public List<MeetingPub> selectMyGrabListByUid(@RequestParam("uid") String uid) {

        return meetingPubService.selectMyGrabListByUid(uid);
    }

}
