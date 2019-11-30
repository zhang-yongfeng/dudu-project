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

    /**
     * 我的抢单者列表
     * 显示的都是发单数据的详细列表
     *
     * arg0,arg1 / param1,param2 来传递参数
     *
     * tname=-1代表用户要查询全部类别
     * tname=java/ui/数据库
     */
    List<MeetingPub> selectGrabList(String uid,String tname);

    /**
     * 我的抢单列表
     * 会议-->会议抢单-->我的抢单
     * @param uid 抢单人的UID
     * @return
     */
    List<MeetingPub> selectMyGrabListByUid(String uid);
}