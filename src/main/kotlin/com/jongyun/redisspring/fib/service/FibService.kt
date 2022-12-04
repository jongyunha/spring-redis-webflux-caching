package com.jongyun.redisspring.fib.service

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class FibService {

    // have a strategy for cache evict
    @Cacheable("math:fib", key = "#index")
    fun getFib(index: Int, name: String): Int {
        println("calcurating fib for $index name: $name")
        return this.fib(index)
    }

    fun fib(number: Int): Int {
        if (number < 2) return number
        return fib(number - 1) + fib(number - 2)
    }
}