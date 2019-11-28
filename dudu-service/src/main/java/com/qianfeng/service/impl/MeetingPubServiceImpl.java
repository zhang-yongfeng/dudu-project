package com.qianfeng.service.impl;

import com.qianfeng.mapper.MeetingPubMapper;
import com.qianfeng.po.MeetingPub;
import com.qianfeng.service.MeetingPubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/28
 * @Time:18:55
 */
@Service
public class MeetingPubServiceImpl implements MeetingPubService {
    @Autowired
    private MeetingPubMapper meetingPubMapper;
    @Override
    public int insertWeixinSelective(MeetingPub meetingPub) {
        //UUID当主键
        meetingPub.setId(UUID.randomUUID().toString());
        //按规则生成pcode
        meetingPub.setPcode(pcodeGen(meetingPub.getPtime()));

        meetingPub.setCreatedate(new Date());

        meetingPub.setStatus((short) 1);

        return meetingPubMapper.insertSelective(meetingPub);
    }

    @Override
    public List<MeetingPub> selectMeetingPubByUid(String uid) {
        return meetingPubMapper.selectMeetingPubByUid(uid);
    }

    /**
     * 会议编号生成规则
     * 根据会议召开日期进行生成
        1.时间进行字符串截取
        2.根据时间字符串 去数据库查询
            如果查询结果为null
            如果查询结果不为null,将最大的单号取出
            对其进行+1就是新的单号
     */
    private String pcodeGen(String ptime){
        String str = ptime.substring(0,10);
        str = str.replaceAll("-","");

        String result = meetingPubMapper.selectMsxPcodeByTime(str);
        if (StringUtils.isEmpty(result)){
            return str+"001";
        }else {
            Long l = Long.parseLong(result)+1;
            return l.toString();
        }

    }
}
