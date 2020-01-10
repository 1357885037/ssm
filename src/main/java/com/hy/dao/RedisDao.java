package com.hy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao {

    @Autowired
    private RedisTemplate redisTemplate;

    public void saveOrUpdateString(String k, String v) {
        redisTemplate.opsForValue().set(k, v);
    }

    public Object getString(String k) {
        return redisTemplate.opsForValue().get(k);
    }


}
