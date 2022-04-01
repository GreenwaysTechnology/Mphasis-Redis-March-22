package com.mycom.redis.demos;

import org.redisson.api.RListReactive;
import org.redisson.client.codec.LongCodec;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ListImplementation {
    public static void main(String[] args) {
        RListReactive<Object> list = RedisconnectionUtil.getReactiveClient().getList("numbers-input", LongCodec.INSTANCE);
        //push data.
        List<Long> longList = LongStream.rangeClosed(1, 20)
                .boxed()
                .collect(Collectors.toList());
         list.addAll(longList).subscribe();

    }
}
