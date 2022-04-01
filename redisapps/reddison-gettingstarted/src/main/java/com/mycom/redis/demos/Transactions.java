package com.mycom.redis.demos;

import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RTransaction;
import org.redisson.api.TransactionOptions;

public class Transactions {
    public static void main(String[] args) {
        //Without Transaction
        RBucket<String> redisson = RedisconnectionUtil.getClient().getBucket("test");
        redisson.set("123");
        //
        RTransaction transaction = RedisconnectionUtil.getClient().createTransaction(TransactionOptions.defaults());
        //grouping two differnt operation as single execution-transaction
        RBucket<String> bucket = transaction.getBucket("test");
        bucket.set("234");
        RMap<String, String> map = transaction.getMap("myMap");
        map.put("1", "2");
        transaction.commit();
    }
}
