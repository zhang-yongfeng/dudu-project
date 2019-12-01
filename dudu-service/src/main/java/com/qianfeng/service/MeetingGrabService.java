package com.qianfeng.service;

import com.qianfeng.po.MeetingGrab;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    /**
     * 微信端   会议发布--》 我的会议--》选择讲者
     * 根据会议发单id  查询抢单人列表信息
     * @param pid   会议发单ID
     * @return
     */
    List<MeetingGrab> selectGrabListByPid(String pid);

    /**
     * 就选你功能
     * 1.先将所有的抢单  根据pid改成  匹配失败  grabStatus=2
     * 2.再将指定的用户（作为讲者）改成匹配成功  grabStatus=1
     */
    int chooseMeetingGrab(String pid,String uid) throws RuntimeException;
}
