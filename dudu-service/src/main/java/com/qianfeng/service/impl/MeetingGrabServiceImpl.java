package com.qianfeng.service.impl;

import com.qianfeng.mapper.MeetingGrabMapper;
import com.qianfeng.po.MeetingGrab;
import com.qianfeng.service.MeetingGrabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
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

    /**
     * 微信端   会议发布--》 我的会议--》选择讲者
     * 根据会议发单id  查询抢单人列表信息
     * @param pid   会议发单ID
     * @return
     */
    @Override
    public List<MeetingGrab> selectGrabListByPid(String pid) {
        return meetingGrabMapper.selectGrabListByPid(pid);
    }

    /**
     *
     * @param pid
     * @param uid
     *
     * Spring事务机制   遇到运行时异常  会回滚事务
     * @return
     */
    @Override
    @Transactional()
    public int chooseMeetingGrab(String pid, String uid) throws RuntimeException{

        int num = meetingGrabMapper.updateMeetingGrabMatchFail(pid);
        if (num<1){
            throw new RuntimeException("抢单匹配失败异常");
        }

        int n = meetingGrabMapper.updateMeetingGrabMatchSucc(pid, uid);
        if (n<1){
            throw new RuntimeException("抢单匹配成功异常");
        }
        return 1;
    }
}
