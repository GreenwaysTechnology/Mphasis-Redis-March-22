package com.mycom.redis.demos;

import org.redisson.api.ExpiredObjectListener;
import org.redisson.api.RBucketReactive;
import org.redisson.client.codec.StringCodec;

import java.util.concurrent.TimeUnit;

public class EventListeners {
    public static void main(String[] args) {
        RBucketReactive<String> bucket = RedisconnectionUtil.getReactiveClient().getBucket("user:1:name", StringCodec.INSTANCE);
        //setting value with 10 seconds ttl
        bucket.set("sam", 10, TimeUnit.SECONDS).subscribe();
        //Register listener which listens for redis events
//        bucket.addListener(new ExpiredObjectListener() {
//            @Override
//            public void onExpired(String s) {
//                System.out.println("Expired : " + s);
//            }
//        }).subscribe();
        bucket.addListener((ExpiredObjectListener) s -> System.out.println("Expired : " + s))
                .subscribe();

        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
