package com.mycom.redis.demos;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;

import java.util.concurrent.TimeUnit;

public class TimetoLiveKeys {
    public static void main(String[] args) {
        RedissonClient redissonClient = RedisconnectionUtil.getClient();
        RBucket<String> secretKey = redissonClient.getBucket("user:otp", StringCodec.INSTANCE);
        secretKey.set("3df45",50, TimeUnit.SECONDS);

        //extends
        secretKey.expire(60, TimeUnit.SECONDS);
        long remainTimeToLive = secretKey.remainTimeToLive();
        System.out.println(remainTimeToLive);

    }
}
