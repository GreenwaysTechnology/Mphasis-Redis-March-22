package com.mycom.redis.demos;

import org.redisson.api.RBlockingDequeReactive;
import org.redisson.client.codec.LongCodec;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Publisher {
    public static void main(String[] args) {
        RBlockingDequeReactive<Object> msgQueue = RedisconnectionUtil.getReactiveClient().getBlockingDeque("message-queue", LongCodec.INSTANCE);
        Flux.range(1, 100)
                .delayElements(Duration.ofMillis(500))
                .doOnNext(i -> System.out.println("going to add " + i))
                .flatMap(i -> msgQueue.add(Long.valueOf(i)))
                .subscribe();
        sleep(600_000);
    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
