package com.jongyun.redisspring.city.controller

import com.jongyun.redisspring.city.dto.City
import com.jongyun.redisspring.city.service.CityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class CityController(
    private val cityService: CityService
) {

    @GetMapping("/city/{zipCode}")
    fun getCity(
        @PathVariable zipCode: String
    ): Mono<City> {
        return this.cityService.getCity(zipCode)
    }
}