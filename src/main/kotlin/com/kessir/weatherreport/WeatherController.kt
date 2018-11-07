package com.kessir.weatherreport

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WeatherController {

    @GetMapping("/greeting")
    fun getWeather(): String {
        return "Hello world"
    }
}
