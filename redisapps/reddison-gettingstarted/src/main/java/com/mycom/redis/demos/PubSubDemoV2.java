package com.mycom.redis.demos;

import org.redisson.api.RTopicReactive;
import org.redisson.client.codec.StringCodec;

public class PubSubDemoV2 {
    public static void main(String[] args) {
        //consumer code
        RTopicReactive topic = RedisconnectionUtil.getReactiveClient().getTopic("slack-room1", StringCodec.INSTANCE);
        topic.getMessages(String.class)
                .doOnError(System.out::println)
                .doOnNext(System.out::println)
                .subscribe();

    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
