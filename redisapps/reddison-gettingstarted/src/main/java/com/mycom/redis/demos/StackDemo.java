package com.mycom.redis.demos;

import org.redisson.api.RDequeReactive;
import org.redisson.client.codec.LongCodec;

public class StackDemo {
    public static void main(String[] args) {
        RDequeReactive<Object> stack = RedisconnectionUtil.getReactiveClient().getDeque("numbers-input", LongCodec.INSTANCE);
        stack.pollLast().repeat(3).subscribe();
    }
}
