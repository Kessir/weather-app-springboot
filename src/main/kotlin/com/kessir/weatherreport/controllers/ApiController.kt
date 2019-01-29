package com.kessir.weatherreport.controllers

import com.kessir.weatherreport.data.WeatherRepository
import com.kessir.weatherreport.data.model.Location
import com.kessir.weatherreport.data.model.LocationTemps
import com.kessir.weatherreport.services.AppDataService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class ApiController(val appDataService: AppDataService, var weatherRepository: WeatherRepository) {

    @GetMapping("/locations")
    fun getLocations(): List<Location> {
        return appDataService.getLocations()
    }

    @GetMapping("/weather")
    fun getWeather(): List<LocationTemps> {
        return weatherRepository.findAll().toList()
    }
}
