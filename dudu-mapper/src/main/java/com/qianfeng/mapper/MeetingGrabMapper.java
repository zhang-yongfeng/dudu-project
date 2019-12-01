package com.qianfeng.mapper;

import com.qianfeng.po.MeetingGrab;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingGrabMapper {
    int deleteByPrimaryKey(String id);

    int insert(MeetingGrab record);

    int insertSelective(MeetingGrab record);

    MeetingGrab selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MeetingGrab record);

    int updateByPrimaryKey(MeetingGrab record);

    /**
     * 根据会议发单id   查询抢单人的列表信息
     */
    List<MeetingGrab> selectGrabListByPid(String pid);

    /**
     * 就选你功能
     * 1.先将所有的抢单  根据pid改成  匹配失败  grabStatus=2
     * 2.再将指定的用户（作为讲者）改成匹配成功  grabStatus=1
     */
    @Update("update meetinggrab set grabStatus=2,grabDate=NOW() where pid=#{pid}")
    int updateMeetingGrabMatchFail(String pid);

    @Update("update meetinggrab set grabStatus=1,grabDate=NOW() where pid=#{pid} and uid=#{uid}")
    int updateMeetingGrabMatchSucc(String pid,String uid);
}