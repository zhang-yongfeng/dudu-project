package com.qianfeng.service;

import com.qianfeng.po.MeetingType;

import java.util.List;

/**
 * @Description:
 * @Company: qianfeng
 * @Author: ZhangYongFeng
 * @Date: 2019/11/28
 * @Time:20:01
 */
public interface MeetingTypeService {

    List<MeetingType> selectByStatusAndSortNumASC();
}
