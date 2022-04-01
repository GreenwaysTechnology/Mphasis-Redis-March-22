package com.mycom.redis.demos;

import org.redisson.api.BatchOptions;
import org.redisson.api.RBatchReactive;
import org.redisson.api.RListReactive;
import org.redisson.api.RSetReactive;
import org.redisson.client.codec.LongCodec;

public class RedisPipeLine {
    public static void main(String[] args) {
        RBatchReactive batch = RedisconnectionUtil.getReactiveClient().createBatch(BatchOptions.defaults());
        RListReactive<Long> list = batch.getList("numbers-list", LongCodec.INSTANCE);
        RSetReactive<Long> set = batch.getSet("numbers-set", LongCodec.INSTANCE);

        for (long i = 0; i < 500_000; i++) {
            list.add(i);
            set.add(i);
        }
        batch.execute().doOnTerminate(()->{
            System.out.println("done");
        }).subscribe(System.out::println);
    }
}
