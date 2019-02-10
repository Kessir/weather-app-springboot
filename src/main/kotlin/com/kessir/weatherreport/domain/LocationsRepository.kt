package com.kessir.weatherreport.domain

import com.kessir.weatherreport.domain.model.Location
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationsRepository: CrudRepository<Location, String> {
}
