package com.mycom.redis.demos;

import org.redisson.api.RAtomicLongReactive;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ReactiveClientUserVisit {
    public static void main(String[] args) {
        //redision
        RAtomicLongReactive atomicLong = RedisconnectionUtil.getReactiveClient()
                .getAtomicLong("user:visit:1");
        //reactor api
        Flux.range(1,50)
                .delayElements(Duration.ofSeconds(1)) //emit numbers after every 1 second
                .flatMap(integer -> atomicLong.incrementAndGet())
                .subscribe(System.out::println);

    }

}
