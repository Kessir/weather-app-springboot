package com.kessir.weatherreport.services

import com.kessir.weatherreport.domain.WeatherRepository
import com.kessir.weatherreport.domain.model.Location
import com.kessir.weatherreport.domain.model.LocationTemps
import org.springframework.stereotype.Service

@Service
class TemperaturesService(private val weatherRepository: WeatherRepository) {

    fun deleteAll() {
        weatherRepository.deleteAll()
    }

    fun getAll(): List<LocationTemps> {
        return weatherRepository.findAll().toList()
    }
}
