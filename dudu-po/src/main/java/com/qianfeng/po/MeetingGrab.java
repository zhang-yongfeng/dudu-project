package com.qianfeng.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MeetingGrab implements Serializable {
    /**主键ID*/
    private String id;
    /**发单ID*/
    private String pid;
    /**抢单描述信息*/
    private String remark;
    /**抢单者ID*/
    private String uid;
    /**抢单时间*/
    private Date createdate;
    /**匹配状态
     * 0  未匹配
     * 1  匹配成功
     * 2  匹配失败
     */
    private Integer grabstatus;
    /**匹配时间*/
    private Date grabdate;
    /**
     * 状态
     * 0  无效
     * 1  有效
     */
    private Short status;

    /**
     * 抢单人 关联  用户信息
     */
    private User user;

}