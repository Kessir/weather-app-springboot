package com.kessir.weatherreport.controllers

import com.kessir.weatherreport.data.AppDataRepository
import com.kessir.weatherreport.data.WeatherRepository
import com.kessir.weatherreport.data.model.Location
import com.kessir.weatherreport.data.model.LocationTemps
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class ApiController {
    @Autowired
    lateinit var appDataRepository: AppDataRepository

    @Autowired
    lateinit var weatherRepository: WeatherRepository
    @Autowired

    @GetMapping("/locations")
    fun getLocations(): List<Location> {
        val appData =  appDataRepository.findAll()

        if (appData.toList().isNotEmpty()){
            return appData.toList()[0].locations
        }else {
            return emptyList()
        }
    }

    @GetMapping("/weather")
    fun getWeather(): List<LocationTemps> {
        return weatherRepository.findAll().toList()
    }
}
