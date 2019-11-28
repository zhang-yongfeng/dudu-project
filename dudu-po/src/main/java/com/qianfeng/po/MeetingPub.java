package com.qianfeng.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MeetingPub implements Serializable {
    /**主键ID  UUID*/
    private String id;
    /**会议编号(按规则生成)*/
    private String pcode;

    private String ptime;

    private String tname;

    private String ptitle;

    private String pzone;

    private String uid;
    /**描述*/
    private String remark;

    private Date createdate;

    private Short status;


}