package com.kessir.weatherreport.domain

import com.kessir.weatherreport.domain.model.LocationTemps
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WeatherRepository: CrudRepository<LocationTemps, String> {
}
