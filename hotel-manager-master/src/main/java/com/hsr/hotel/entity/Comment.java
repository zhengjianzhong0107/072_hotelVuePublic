package com.hsr.hotel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author makejava
 * @since 2021-12-29 19:35:22
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = -30199789332622297L;
    
    private Integer id;
    /**
     * 客户id
     */
    private Integer userId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 酒店id
     */
    private Integer typeId;
    /**
     * 点赞数
     */
    private Integer approve;
    /**
     * 评论时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private User user;

    private RoomType room;

    public Comment(Integer typeId, String content) {
        this.typeId=typeId;
        this.content=content;
    }


    public RoomType getRoom() {
        return room;
    }

    public void setRoom(RoomType room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getApprove() {
        return approve;
    }

    public void setApprove(Integer approve) {
        this.approve = approve;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

