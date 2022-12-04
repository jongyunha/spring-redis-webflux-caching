package com.jongyun.redisspring.weather.service

import java.util.stream.IntStream
import org.springframework.cache.annotation.Cacheable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class WeatherService(
    private val externalServiceClient: ExternalServiceClient
) {

    @Cacheable("weather")
    fun getInfo(zip: Int): Int {
        return 0
    }

    @Scheduled(fixedRate = 10_000)
    fun update() {
        println("updating weather")
        IntStream.rangeClosed(1, 5)
            .forEach { externalServiceClient.getWeatherInfo(it) }
    }
}