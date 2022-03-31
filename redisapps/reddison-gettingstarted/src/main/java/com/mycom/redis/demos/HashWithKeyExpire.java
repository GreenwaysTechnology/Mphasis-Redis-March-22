package com.mycom.redis.demos;

import org.redisson.api.RMapCacheReactive;
import org.redisson.codec.TypedJsonJacksonCodec;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HashWithKeyExpire {
    public static void main(String[] args) {
        TypedJsonJacksonCodec codec = new TypedJsonJacksonCodec(Integer.class, Student.class);
        RMapCacheReactive<Integer, Student> mapCache = RedisconnectionUtil.getReactiveClient().getMapCache("users:cache", codec);
        //TTL For Hash keys
        Student student1 = new Student("sam", 10, "atlanta", List.of(1, 2, 3));
        Student student2 = new Student("jake", 30, "miami", List.of(10, 20, 30));

        mapCache.put(1,student1,5, TimeUnit.SECONDS).subscribe();
        mapCache.put(2, student2, 10, TimeUnit.SECONDS).subscribe();

    }
}
