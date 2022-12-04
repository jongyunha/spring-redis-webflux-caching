package com.jongyun.redisspring.weather.controller

import com.jongyun.redisspring.weather.service.WeatherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class WeatherController(
    private val weatherService: WeatherService
) {

    @GetMapping("/weather/{zip}")
    fun getWeather(@PathVariable zip: Int): Mono<Int> {
        return Mono.fromSupplier { weatherService.getInfo(zip) }
    }
}