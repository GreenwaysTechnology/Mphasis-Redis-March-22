package com.mycom.redis.demos;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

public class SerilzationValues {
    public static void main(String[] args) {
        RedissonClient redissonClient = RedisconnectionUtil.getClient();
        //send key and value .
        //create key
        RBucket<Object> bucket = redissonClient.getBucket("user:name");
        //set value
        bucket.set("subramanian");
        System.out.println( "key : => " + bucket.get());
    }
}
