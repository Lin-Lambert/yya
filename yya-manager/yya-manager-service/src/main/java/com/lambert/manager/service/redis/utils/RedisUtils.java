package com.lambert.manager.service.redis.utils;

public interface RedisUtils {

    //保存
    public String set(String key, String value);

    //查询
    public String get(String key);

    //删除
    public Long del(String key);

    //设置键并设置生存时间
    public Long expire(String key, String value, Integer second);

    //更改生存时间
    public Long expire(String key, Integer second);
}
