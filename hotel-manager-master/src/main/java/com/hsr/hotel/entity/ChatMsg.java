package com.hsr.hotel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 消息
 */@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMsg {
     private String from;
     private String to;
     private String content;
     private LocalDateTime date;
     protected String fromNickName;
}
