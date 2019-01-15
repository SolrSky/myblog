package com.mtrhz.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Redis 操作工具类
 * @author Solrsky
 * @date 2019/1/14
 */
public class RedisUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置缓存，默认过期时间1天
     * @param key
     * @param Value
     */
    public void set(final String key, final Object Value){
        redisTemplate.opsForValue().set(key, Value);
        redisTemplate.expire(key, 1, TimeUnit.DAYS);
    }

    /**
     * 根据key获取缓存值
     * @param key
     * @return
     */
    public Object get(final String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置缓存，并设置过期时间，单位为秒
     * @param key
     * @param value
     * @param expireTime
     */
    public void set(final String key, final Object value, final long expireTime){
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 删除指定key的缓存
     * @param key
     * @return
     */
    public void delete(final String key){
        redisTemplate.delete(key);
    }

    /**
     * 获取redisTemplate实例，方便操作
     * @return
     */
    public RedisTemplate<String, Object> getRedisTemplate(){
        return redisTemplate;
    }
}
