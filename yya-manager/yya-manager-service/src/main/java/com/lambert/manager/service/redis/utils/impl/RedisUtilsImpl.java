package com.lambert.manager.service.redis.utils.impl;

import com.lambert.manager.service.redis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class RedisUtilsImpl implements RedisUtils {

    @Autowired
    public ShardedJedisPool shardedJedisPool;

    public <T> T execute(Function<ShardedJedis,T> function){
        ShardedJedis resource = null;
        try {
            resource = shardedJedisPool.getResource();
            return function.callBack(resource);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resource != null) {
                resource.close();
            }
        }
        shardedJedisPool.close();
        return null;
    }

    @Override
    public String set(final String key, final String value) {
        return this.execute(new Function<ShardedJedis, String>() {
            @Override
            public String callBack(ShardedJedis shardedJedis) {
                return shardedJedis.set(key, value);
            }
        });
    }

    @Override
    public String get(final String key) {
        return this.execute(new Function<ShardedJedis, String>() {
            @Override
            public String callBack(ShardedJedis shardedJedis) {
                return shardedJedis.get(key);
            }
        });
    }

    @Override
    public Long del(final String key) {
        return this.execute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callBack(ShardedJedis shardedJedis) {
                return shardedJedis.del(key);
            }
        });
    }

    @Override
    public Long expire(final String key, final String value, final Integer second) {
        return this.execute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callBack(ShardedJedis shardedJedis) {
                shardedJedis.set(key,value);
                return shardedJedis.expire(key,second);
            }
        });
    }

    @Override
    public Long expire(final String key, final Integer second) {
        return this.execute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callBack(ShardedJedis shardedJedis) {
                return shardedJedis.expire(key, second);
            }
        });
    }
}
