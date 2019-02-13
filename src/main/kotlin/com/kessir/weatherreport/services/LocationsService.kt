package com.kessir.weatherreport.services

import com.kessir.weatherreport.domain.LocationsRepository
import com.kessir.weatherreport.domain.model.Location
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class LocationsService(private val locationsRepository: LocationsRepository) {
    fun getAll(): List<Location> {
        return locationsRepository.findAll().toList()
    }

    fun saveAll(locations: List<Location>) {
        locationsRepository.saveAll(locations)
    }

    fun findById(id: String): Location? =
            locationsRepository.findByIdOrNull(id)
}
