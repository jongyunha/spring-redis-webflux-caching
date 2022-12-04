package com.jongyun.redisspring.fib.service

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class FibService {

    // have a strategy for cache evict
    @Cacheable("math:fib", key = "#index")
    fun getFib(index: Int, name: String): Int {
        println("calcurating fib for $index name: $name")
        return this.fib(index)
    }

    @CacheEvict("math:fib", key = "#index")
    fun clearCache(index: Int) {
        println("clearing hash key")
    }

//    @Scheduled(fixedRate = 10_000)
    @CacheEvict("math:fib", allEntries = true)
    fun clearCache() {
        println("clearing all fib keys")
    }

    fun fib(number: Int): Int {
        if (number < 2) return number
        return fib(number - 1) + fib(number - 2)
    }
}