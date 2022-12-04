package com.jongyun.redisspring.fib.controller

import com.jongyun.redisspring.fib.service.FibService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class FibController(
    private val fibService: FibService
) {

    @GetMapping("/fib/{index}/{name}")
    fun getFib(
        @PathVariable index: Int, @PathVariable name: String
    ): Mono<Int> {
        return Mono.fromSupplier { fibService.getFib(index, name) }
    }
}