package com.mycom.redis.demos;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;

public class StoreObject {
    public static void main(String[] args) {
        RedissonClient redissonClient = RedisconnectionUtil.getClient();
        RBucket<Customer> bucket = redissonClient.getBucket("customer:info", new TypedJsonJacksonCodec(Customer.class));
        bucket.set(new Customer(1, "Subramanian", "Coimbatore"));
        Customer customer=bucket.get();
        System.out.println(customer.toString());

    }
}
