package com.mycom.redis.demos;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class ConnectToRedis {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RKeys keys = redissonClient.getKeys();
        keys.getKeys().forEach(data -> System.out.println("keys" + data));

    }
}
