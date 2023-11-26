package com.zdk.redis.delayQueue;

import lombok.Data;

import java.util.Date;

@Data
public class MassMailTask {

    //相关任务id
    private Long taskId;
    //延迟任务的开始时间
    private Date startTime;

}
