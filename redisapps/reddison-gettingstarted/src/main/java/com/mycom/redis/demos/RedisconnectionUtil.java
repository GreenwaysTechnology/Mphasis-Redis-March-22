package com.mycom.redis.demos;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;

public class RedisconnectionUtil {
    static RedissonClient redissonClient;

    public static RedissonClient getClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        RedissonClient client = Redisson.create(config);
        redissonClient = Redisson.create(config);
        return redissonClient;
    }
    public static RedissonReactiveClient getReactiveClient(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        RedissonClient client = Redisson.create(config);
        redissonClient = Redisson.create(config);
        return redissonClient.reactive();
    }
}
