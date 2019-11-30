package com.qianfeng.service.impl;

import com.qianfeng.mapper.MeetingGrabMapper;
import com.qianfeng.po.MeetingGrab;
import com.qianfeng.service.MeetingGrabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/30
 * @Time:19:16
 */
@Service
public class MeetingGrabServiceImpl implements MeetingGrabService {
    @Autowired
    private MeetingGrabMapper meetingGrabMapper;

    @Override
    public int insertSelectiveWeixin(MeetingGrab meetingGrab) {

        meetingGrab.setId(UUID.randomUUID().toString());
        meetingGrab.setCreatedate(new Date());
        meetingGrab.setGrabstatus(0);       //默认为0 未匹配状态
        meetingGrab.setStatus((short) 1);

        return meetingGrabMapper.insertSelective(meetingGrab);
    }
}
