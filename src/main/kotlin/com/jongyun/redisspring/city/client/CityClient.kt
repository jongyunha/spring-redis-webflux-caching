package com.jongyun.redisspring.city.client

import com.jongyun.redisspring.city.dto.City
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class CityClient(
    @Value("\${city.service.url}")
    val url: String
) {

    val webClient = WebClient.builder()
        .baseUrl(url)
        .build()

    fun getCity(zipCode: String): Mono<City> {
        return this.webClient.get()
            .uri("{zipcode}", zipCode)
            .retrieve()
            .bodyToMono(City::class.java)
    }
}