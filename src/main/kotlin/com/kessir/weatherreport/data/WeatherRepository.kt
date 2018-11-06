package com.kessir.weatherreport.data

import com.kessir.weatherreport.data.model.DailyWeather
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface WeatherRepository: CrudRepository<DailyWeather, String> {
}