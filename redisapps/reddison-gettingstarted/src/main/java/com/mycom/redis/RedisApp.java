package com.mycom.redis;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import redis.clients.jedis.JedisPooled;

public class RedisApp {
    public static void main(String[] args) {
        //connects to default ip address and port
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        RedissonClient client = Redisson.create(config);

        RedissonClient redissonClient = Redisson.create(config);
        RKeys keys = redissonClient.getKeys();
        keys.getKeys().forEach(System.out::println);
//        JedisPooled jedis = new JedisPooled("localhost", 6379);
//        String message = jedis.get("message");
//        System.out.println(message);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
