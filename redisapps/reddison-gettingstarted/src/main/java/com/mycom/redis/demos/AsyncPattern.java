package com.mycom.redis.demos;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RFuture;

public class AsyncPattern {
    public static void getSync() {
        RAtomicLong longObject = RedisconnectionUtil.getClient().getAtomicLong("user:2:counter");
// sync way
        System.out.println("Start");
        boolean res = longObject.compareAndSet(9000, 9000);
        System.out.println(res);
        System.out.println("end");
    }

    public static void main(String[] args) {
          // getSync();
             getAsync();

    }

    private static void getAsync() {
        //before running : go to redis and add key
        //set user:2:counter 9000
        //after setting test
        RAtomicLong longObject = RedisconnectionUtil.getClient().getAtomicLong("user:2:counter");
// sync way
        System.out.println("Start");
        RFuture<Boolean> booleanRFuture = longObject.compareAndSetAsync(9000, 9000);
        System.out.println("end");

        booleanRFuture.thenAccept(res -> {
            System.out.println("Got Result " + res);
        }).exceptionally(err -> {
            System.out.println("Got Error " + err);
            return null;
        });
    }
}
