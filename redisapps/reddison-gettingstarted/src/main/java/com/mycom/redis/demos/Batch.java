package com.mycom.redis.demos;

import org.redisson.api.BatchOptions;
import org.redisson.api.RBatchReactive;
import org.redisson.api.RListReactive;
import org.redisson.api.RSetReactive;
import org.redisson.client.codec.LongCodec;
import reactor.core.publisher.Flux;

public class Batch {
    public static void main(String[] args) {
        //batchTest();
       regularTest();
    }

    public static  void batchTest() {
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

    public static void regularTest() {
        RListReactive<Long> list = RedisconnectionUtil.getReactiveClient().getList("numbers-list", LongCodec.INSTANCE);
        RSetReactive<Long> set = RedisconnectionUtil.getReactiveClient().getSet("numbers-set", LongCodec.INSTANCE);

        Flux.range(1, 500_000)
                .map(Long::valueOf)
                .flatMap(i -> list.add(i).then(set.add(i)))
                .subscribe();

    }
}
