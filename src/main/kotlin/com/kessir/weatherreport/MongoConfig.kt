package com.kessir.weatherreport

import com.kessir.weatherreport.data.WeatherRepository
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@EnableMongoRepositories
@Configuration
class MongoConfig {
}
