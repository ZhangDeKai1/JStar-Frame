package com.zdk.redis.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * RedisUtil工具类,在这里统一再封装一次，redisTemplate的命令更见名知意
 */
@Component
public class RedisUtil {

    private static final String CACHE_KEY_SEPARATOR = ".";

    @Resource
    private RedisTemplate redisTemplate;

    private DefaultRedisScript<Boolean> casScript;

    //提前加载lua脚本,用redis加lua实现cas
    @PostConstruct
    public void init(){
        casScript=new DefaultRedisScript<>();
        casScript.setResultType(Boolean.class);
        casScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("compareAndSet.lua")));
    }

    public Boolean compareAndSet(String key,Long oldValue,Long newValue){
        List<String> keys=new ArrayList<>();
        keys.add(key);
        return (Boolean) redisTemplate.execute(casScript,keys,oldValue,newValue);
    }

    /**
     * 构建缓存key
     */
    public String buildKey(String... strObjs) {//String...表示参数可以传String数组也可以传多个字符串
        return Stream.of(strObjs).collect(Collectors.joining(CACHE_KEY_SEPARATOR));
    }

    /**
     * 是否存在key
     */
    public boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除key
     */
    public boolean del(String key) {
        return redisTemplate.delete(key);
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public boolean setNx(String key, String value, Long time, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
    }

    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public Boolean zAdd(String key, String value, Long score) {
        return redisTemplate.opsForZSet().add(key, value, Double.valueOf(String.valueOf(score)));
    }

    public Long countZset(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    public Set<String> rangeZset(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    public Long removeZset(String key, Object value) {
        return redisTemplate.opsForZSet().remove(key, value);
    }

    public void removeZsetList(String key, Set<String> value) {
        value.stream().forEach((val) -> redisTemplate.opsForZSet().remove(key, val));
    }

    public Double score(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    public Set<String> rangeByScore(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeByScore(key, Double.valueOf(String.valueOf(start)), Double.valueOf(String.valueOf(end)));
    }

    public Object addScore(String key, Object obj, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, obj, score);
    }

    public Object rank(String key, Object obj) {
        return redisTemplate.opsForZSet().rank(key, obj);
    }

}