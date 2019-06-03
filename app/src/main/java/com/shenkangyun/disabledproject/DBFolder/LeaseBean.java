package com.shenkangyun.disabledproject.DBFolder;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/3/8.
 */

public class LeaseBean extends DataSupport {
    private int id;
    private int applicationtypeId;
    private String title;
    private int orderNum;
    private String content;
    private String createTime;
    private int delFlag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApplicationtypeId() {
        return applicationtypeId;
    }

    public void setApplicationtypeId(int applicationtypeId) {
        this.applicationtypeId = applicationtypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
