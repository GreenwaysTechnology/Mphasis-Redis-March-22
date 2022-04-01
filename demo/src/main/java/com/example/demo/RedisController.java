package com.example.demo;

import org.redisson.api.RBucket;
import org.redisson.api.RBucketReactive;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RedisController {

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/hello")
    public String getMessage() {
        RBucket<String> message = redissonClient.getBucket("message", StringCodec.INSTANCE);
        return message.get();
    }

    @GetMapping("/helloreactive")
    public Mono<String> getMessageReactive() {
        RBucketReactive<String> message = redissonClient.reactive().getBucket("message", StringCodec.INSTANCE);
        return message.get();

    }
}
