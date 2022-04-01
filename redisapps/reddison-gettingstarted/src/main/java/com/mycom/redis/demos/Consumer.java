package com.mycom.redis.demos;
import org.redisson.api.RBlockingDequeReactive;
import org.redisson.client.codec.LongCodec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Consumer {
    public static void main(String[] args) {
        RBlockingDequeReactive<Long> msgQueue = RedisconnectionUtil.getReactiveClient().getBlockingDeque("message-queue", LongCodec.INSTANCE);

        msgQueue.takeElements()
                .doOnNext(i -> System.out.println("Consumer 1 : " + i))
                .doOnError(System.out::println)
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
