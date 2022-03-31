package com.mycom.redis.demos;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.RLocalCachedMap;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;

import java.util.List;

public class ServiceTwo {
    public static void main(String[] args) {
        RLocalCachedMap<Integer, Student> studentsMap;
        RedissonClient redissonClient = RedisconnectionUtil.getClient();

        LocalCachedMapOptions<Integer, Student> mapOptions = LocalCachedMapOptions.<Integer, Student>defaults()
                .syncStrategy(LocalCachedMapOptions.SyncStrategy.UPDATE)
                .reconnectionStrategy(LocalCachedMapOptions.ReconnectionStrategy.NONE);

        studentsMap = redissonClient.getLocalCachedMap(
                "students",
                new TypedJsonJacksonCodec(Integer.class, Student.class),
                mapOptions
        );
        Student student1 = new Student("sam", 10, "atlanta", List.of(1, 2, 3));
        //something change
        student1.setName("Subramanian M");
        studentsMap.put(1, student1);
    }
}
