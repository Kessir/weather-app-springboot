package com.kessir.weatherreport.domain

import com.kessir.weatherreport.domain.model.AppData
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AppDataRepository: CrudRepository<AppData, String> {
}
