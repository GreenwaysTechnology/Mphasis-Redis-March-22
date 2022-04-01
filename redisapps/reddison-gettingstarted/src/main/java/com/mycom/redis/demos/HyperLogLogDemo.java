package com.mycom.redis.demos;

import org.redisson.api.RHyperLogLogReactive;
import org.redisson.client.codec.LongCodec;
import reactor.core.publisher.Flux;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class HyperLogLogDemo {
    public static void main(String[] args) {
        RHyperLogLogReactive<Long> counter = RedisconnectionUtil.getReactiveClient().getHyperLogLog("user:visits", LongCodec.INSTANCE);
        List<Long> list1 = LongStream.rangeClosed(1, 25000)
                .boxed()
                .collect(Collectors.toList());

        List<Long> list2 = LongStream.rangeClosed(25001, 50000)
                .boxed()
                .collect(Collectors.toList());

        List<Long> list3 = LongStream.rangeClosed(1, 75000)
                .boxed()
                .collect(Collectors.toList());

        List<Long> list4 = LongStream.rangeClosed(50000, 100_000)
                .boxed()
                .collect(Collectors.toList());

        Flux.just(list1, list2, list3, list4)
                .flatMap(counter::addAll)
                .then().subscribe();

        System.out.println("Approxmiate Counters");
        counter.count()
                .doOnNext(System.out::println)
                .subscribe();
    }
}
