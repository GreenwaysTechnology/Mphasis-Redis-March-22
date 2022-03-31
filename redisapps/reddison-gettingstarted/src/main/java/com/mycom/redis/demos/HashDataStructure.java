package com.mycom.redis.demos;

import org.redisson.api.RMap;
import org.redisson.api.RMapReactive;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.TypedJsonJacksonCodec;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class HashDataStructure {
    public static void main(String[] args) {
//        createReactiveHash();
//        createNormalHash();
//        createHashFromSet();
//        createHashFromJavaMap();
        createHashForObjects();
    }

    private static void createHashForObjects() {
        //how to prisit entity class
        TypedJsonJacksonCodec codec = new TypedJsonJacksonCodec(Integer.class, Student.class);
        RMapReactive<Integer, Student> map = RedisconnectionUtil.getReactiveClient().getMap("users", codec);
        Student student1 = new Student("sam", 10, "atlanta", List.of(1, 2, 3));
        Student student2 = new Student("jake", 30, "miami", List.of(10, 20, 30));

        map.put(1, student1).subscribe();
        map.put(2, student2).subscribe();
    }

    private static void createHashFromJavaMap() {
        RMapReactive<String, String> map = RedisconnectionUtil.getReactiveClient().getMap("user:5", StringCodec.INSTANCE);
        Map<String, String> javaMap = Map.of(
                "name", "jake",
                "age", "30",
                "city", "miami"
        );
        map.putAll(javaMap).then().subscribe();
    }



    private static void createHashFromSet() {
        RMap<String, String> map = RedisconnectionUtil.getClient().getMap("somekey", StringCodec.INSTANCE);
        map.put("1","hello");
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("1");
        Map<String, String> all = map.getAll(hashSet);
        all.values().forEach(System.out::println);
    }

    private static void createNormalHash() {
        RMap<String, String> map = RedisconnectionUtil.getClient().getMap("user:2:", StringCodec.INSTANCE);
        map.put("name", "subramanian");
        map.put("age", "42");
        map.put("city", "Coimbatore");
        map.values().forEach(System.out::println);

        //update
        map.put("age","40");
        map.values().forEach(System.out::println);

    }

    private static void createReactiveHash() {
        RMapReactive<Object, Object> map = RedisconnectionUtil.getReactiveClient().getMap("user:1", StringCodec.INSTANCE);
        //set values
        map.put("name", "sam").subscribe();
        map.put("age", "10").subscribe();
        map.put("city", "atlanta").subscribe();
        map.get("name").subscribe(System.out::println);
    }
}
