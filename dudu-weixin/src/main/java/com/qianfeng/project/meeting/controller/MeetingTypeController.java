package com.qianfeng.project.meeting.controller;

import com.qianfeng.mapper.MeetingTypeMapper;
import com.qianfeng.po.MeetingType;
import com.qianfeng.service.MeetingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/28
 * @Time:19:06
 */
@Controller
@RequestMapping("meetingType")
public class MeetingTypeController {

    @Autowired
    private MeetingTypeService meetingTypeService;

    /**
     * 微信会议发布   加载课题类别的数据
     */
    @ResponseBody
    @RequestMapping(method= RequestMethod.POST, value ="list")
    public List<MeetingType> selectMeetingTypeList(){
        return meetingTypeService.selectByStatusAndSortNumASC();
    }
}
