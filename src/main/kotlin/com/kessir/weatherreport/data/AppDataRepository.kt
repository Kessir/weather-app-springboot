package com.kessir.weatherreport.data

import com.kessir.weatherreport.data.model.AppData
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AppDataRepository: CrudRepository<AppData, String> {
}
