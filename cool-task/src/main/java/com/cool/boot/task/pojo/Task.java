package com.cool.boot.task.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Task {

    private Long id;

    private String name;

    private String cron;

    private String params;

    private String notifyContent;

    private Date createTime;

    private Date updateTime;

    private boolean deleted;
}
