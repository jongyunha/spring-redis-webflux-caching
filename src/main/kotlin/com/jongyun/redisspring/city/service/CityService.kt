package com.jongyun.redisspring.city.service

import com.jongyun.redisspring.city.client.CityClient
import com.jongyun.redisspring.city.dto.City
import java.util.concurrent.TimeUnit
import org.redisson.api.RMapCacheReactive
import org.redisson.api.RedissonReactiveClient
import org.redisson.codec.TypedJsonJacksonCodec
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class CityService(
    private val client: RedissonReactiveClient,
    private val cityClient: CityClient
) {

    var cityMap: RMapCacheReactive<String, City> =
        client.getMapCache("city", TypedJsonJacksonCodec(String::class.java, City::class.java))

    // get from cache
    // if empty - get from db / source
    fun getCity(zipCode: String): Mono<City> {
        return this.cityMap.get(zipCode)
            .switchIfEmpty {
                cityClient.getCity(zipCode)
                    .flatMap { this.cityMap.fastPut(zipCode, it, 10, TimeUnit.SECONDS).thenReturn(it) }
            }
    }
}