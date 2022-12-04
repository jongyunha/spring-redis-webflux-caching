package com.jongyun.redisspring.weather.service

import java.util.concurrent.ThreadLocalRandom
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service

@Service
class ExternalServiceClient {

    @CachePut("weather", key = "#zip")
    fun getWeatherInfo(zip: Int): Int {
        return ThreadLocalRandom.current().nextInt(60, 100)
    }
}