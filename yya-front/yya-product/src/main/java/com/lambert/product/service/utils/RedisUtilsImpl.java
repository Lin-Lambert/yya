package com.lambert.product.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


@Service
public class RedisUtilsImpl {

    @Autowired(required = false)
    // 在使用到时才注入
    private ShardedJedisPool shardedJedisPool;

    public <T> T excute(Function<ShardedJedis, T> fun) {
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();

            // 从redis中获取数据
            return fun.callback(shardedJedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) {
                // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
                shardedJedis.close();
            }
        }

        // 关闭连接池
        shardedJedisPool.close();
        return null;
    }

    public String set(final String key,final String value) {
        return this.excute(new Function<ShardedJedis, String>() {
            @Override
            public String callback(ShardedJedis e) {
                return e.set(key, value);
            }

        });
    }

    public String get(final String key) {
        return this.excute(new Function<ShardedJedis, String>() {
            @Override
            public String callback(ShardedJedis e) {
                return e.get(key);
            }
        });
    }

    public Long del(final String key) {
        return this.excute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callback(ShardedJedis e) {
                return e.del(key);
            }
        });
    }

    public Long expire(final String key,final String value,final Integer seconds) {
        return this.excute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callback(ShardedJedis e) {
                e.set(key, value);
                return e.expire(key, seconds);
            }
        });
    }

    public Long expire(final String key,final Integer seconds) {
        return this.excute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callback(ShardedJedis e) {
               
                return e.expire(key, seconds);
            }
        });
    }

}
