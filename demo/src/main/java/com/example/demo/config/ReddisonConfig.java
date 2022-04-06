package com.example.demo.config;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.RedissonSpringCacheManager;
//import org.springframework.cache.CacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReddisonConfig {
    @Bean
    public CacheManager cacheManager(RedissonClient reddisonClient) {
        return new RedissonSpringCacheManager(reddisonClient);
    }
}
