package com.jongyun.redisspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class RedisSpringApplication

fun main(args: Array<String>) {
    runApplication<RedisSpringApplication>(*args)
}
