package com.mycom.redis.demos;

import org.redisson.client.codec.StringCodec;

public class MultiBucket {
    public static void main(String[] args) {
        RedisconnectionUtil.getReactiveClient()
                .getBuckets(StringCodec.INSTANCE)
                .get("user:1:name", "user:2:name", "user:3:name")
                .subscribe(System.out::println);
    }
}
