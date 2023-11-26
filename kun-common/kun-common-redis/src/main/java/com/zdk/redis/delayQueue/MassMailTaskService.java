package com.zdk.redis.delayQueue;

import com.alibaba.fastjson.JSON;
import com.zdk.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MassMailTaskService {
    // 该MassMailTaskService类位于Redis中ZSet结构的key
    private static final String MASS_MAIL_TASK_KEY = "massMailTask";

    @Resource
    private RedisUtil redisUtil;

    public void pushMassMailTaskQueue(MassMailTask massMailTask) {
        Date startTime = massMailTask.getStartTime();
        if (startTime == null) {
            return;
        }
        if (startTime.compareTo(new Date()) <= 0) {
            return;
        }
        log.info("定时群发任务加入延时队列，massMailTask:{}", JSON.toJSON(massMailTask));
        // 使用ZSet数据结构 (key, value, score)
        redisUtil.zAdd(MASS_MAIL_TASK_KEY, massMailTask.getTaskId().toString(), startTime.getTime());
    }

    // 拉取下来的任务可能会很多
    public Set<Long> poolMassMailTaskQueue() {
        // 拉取key为"massMailTask"，时间从0到当前时间的任务
        Set<String> taskIdSet = redisUtil.rangeByScore(MASS_MAIL_TASK_KEY, 0, System.currentTimeMillis());
        log.info("获取延迟群发任务，taskIdSet：{}", JSON.toJSON(taskIdSet));
        if (CollectionUtils.isEmpty(taskIdSet)) {
            return Collections.emptySet();
        }
        redisUtil.removeZsetList(MASS_MAIL_TASK_KEY, taskIdSet);
        return taskIdSet.stream().map(n -> Long.parseLong(n)).collect(Collectors.toSet());
    }
}