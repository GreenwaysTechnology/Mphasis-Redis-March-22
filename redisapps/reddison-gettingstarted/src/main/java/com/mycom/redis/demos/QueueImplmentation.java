package com.mycom.redis.demos;

import org.redisson.api.RQueueReactive;
import org.redisson.client.codec.LongCodec;

public class QueueImplmentation {
    public static void main(String[] args) {
        RQueueReactive<Object> queue = RedisconnectionUtil.getReactiveClient().getQueue("numbers-input", LongCodec.INSTANCE);
        queue.poll().repeat(3).subscribe();

    }
}
