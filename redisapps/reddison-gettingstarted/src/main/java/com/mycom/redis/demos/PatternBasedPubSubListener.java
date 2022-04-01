package com.mycom.redis.demos;

import org.redisson.api.RPatternTopicReactive;
import org.redisson.api.listener.PatternMessageListener;
import org.redisson.client.codec.StringCodec;

public class PatternBasedPubSubListener {
    public static void main(String[] args) {
        RPatternTopicReactive patternTopic = RedisconnectionUtil.getReactiveClient().getPatternTopic("news*", StringCodec.INSTANCE);
        patternTopic.addListener(String.class, new PatternMessageListener<String>() {
            @Override
            public void onMessage(CharSequence pattern, CharSequence topic, String msg) {
                System.out.println(pattern + " : " + topic + " : " + msg);
            }
        }).subscribe();
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
