package com.jongyun.redisspring.city.service

import com.jongyun.redisspring.city.client.CityClient
import com.jongyun.redisspring.city.dto.City
import org.redisson.api.RMapReactive
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

    var cityMap: RMapReactive<String, City> =
        client.getMap("city", TypedJsonJacksonCodec(String::class.java, City::class.java))

    // get from cache
    // if empty - get from db / source
    fun getCity(zipCode: String): Mono<City> {
        return this.cityMap.get(zipCode)
            .switchIfEmpty {
                cityClient.getCity(zipCode)
                    .flatMap { this.cityMap.fastPut(zipCode, it).thenReturn(it) }
            }
    }
}