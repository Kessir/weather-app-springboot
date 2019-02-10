package com.kessir.weatherreport.web

import com.kessir.weatherreport.domain.model.Location
import com.kessir.weatherreport.domain.model.LocationTemps
import com.kessir.weatherreport.services.LocationsService
import com.kessir.weatherreport.services.TemperaturesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class WeatherController(val temperaturesService: TemperaturesService) {

    @GetMapping("/weather")
    fun getWeather(): List<LocationTemps> {
        return temperaturesService.getAll()
    }
}
