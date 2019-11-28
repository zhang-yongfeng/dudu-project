package com.qianfeng.mapper;

import com.qianfeng.po.MeetingType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MeetingTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MeetingType record);

    int insertSelective(MeetingType record);

    MeetingType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetingType record);

    int updateByPrimaryKey(MeetingType record);

    /**
     * 查询课题类别数据信息   有效数据   排序
     */
    @Select("select * from meetingtype where status = 1 order by sortnum")
    List<MeetingType> selectByStatusAndSortNumASC();
}