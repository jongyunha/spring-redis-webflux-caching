package com.jongyun.redisspring

import org.junit.jupiter.api.RepeatedTest
import org.redisson.api.RedissonReactiveClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

@SpringBootTest
class RedisSpringApplicationTests {

    @Autowired
    lateinit var template: ReactiveStringRedisTemplate

    @Autowired
    lateinit var client: RedissonReactiveClient

    @RepeatedTest(3)
    fun springDataRedisTest() {
        val valueOperations = this.template.opsForValue()

        val before = System.currentTimeMillis()
        val mono = Flux.range(1, 500_000)
            .flatMap { valueOperations.increment("user:1:visit") }
            .then()

        StepVerifier.create(mono).verifyComplete()
        val after = System.currentTimeMillis()
        println("${after - before} ms")
    }

    @RepeatedTest(3)
    fun redissonTest() {
        val atomicLong = this.client.getAtomicLong("user:2:visit")

        val before = System.currentTimeMillis()
        val mono = Flux.range(1, 500_000)
            .flatMap { atomicLong.incrementAndGet() }
            .then()

        StepVerifier.create(mono).verifyComplete()
        val after = System.currentTimeMillis()
        println("${after - before} ms")
    }

}
