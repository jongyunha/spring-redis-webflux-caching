package com.jongyun.redisspring.fib.config

import org.redisson.api.RedissonClient
import org.redisson.spring.cache.RedissonSpringCacheManager
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RedissoncacheConfig {

    @Bean
    fun cacheManager(redissonClient: RedissonClient): CacheManager {
        return RedissonSpringCacheManager(redissonClient)
    }
}