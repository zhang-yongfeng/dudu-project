package com.qianfeng.service.impl;

import com.qianfeng.mapper.MeetingTypeMapper;
import com.qianfeng.po.MeetingType;
import com.qianfeng.service.MeetingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/28
 * @Time:20:02
 */
@Service
public class MeetingTypeServiceImpl implements MeetingTypeService {

    @Autowired
    private MeetingTypeMapper meetingTypeMapper;

    @Override
    public List<MeetingType> selectByStatusAndSortNumASC() {
        return meetingTypeMapper.selectByStatusAndSortNumASC();
    }
}
