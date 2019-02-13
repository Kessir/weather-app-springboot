package com.kessir.weatherreport.services

import com.kessir.weatherreport.domain.WeatherRepository
import com.kessir.weatherreport.domain.model.LocationTemps
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TemperaturesService(private val weatherRepository: WeatherRepository) {

    fun deleteAll() {
        weatherRepository.deleteAll()
    }

    fun getAll(): Iterable<LocationTemps> {
        return weatherRepository.findAll()
    }

    fun findById(id: String): LocationTemps? =
            weatherRepository.findByIdOrNull(id)
}
