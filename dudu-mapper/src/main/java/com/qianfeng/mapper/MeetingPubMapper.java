package com.qianfeng.mapper;

import com.qianfeng.po.MeetingPub;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingPubMapper {
    int deleteByPrimaryKey(String id);

    int insert(MeetingPub record);

    int insertSelective(MeetingPub record);

    MeetingPub selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MeetingPub record);

    int updateByPrimaryKey(MeetingPub record);

    /**
     * 会议编号生成规则
     * 根据召开日期年月日 去查询pcode值
     */
    @Select("select max(pcode) from meetingpub where left(pcode,8)=#{time}")
    String selectMsxPcodeByTime(String time);

    /**
     * 我的会议   根据发单人id
     */
    @Select("select * from meetingpub where uid=#{uid} and status=1 order by pcode desc")
    List<MeetingPub> selectMeetingPubByUid(String uid);
}