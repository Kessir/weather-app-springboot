package com.kessir.weatherreport

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@EnableMongoRepositories
@Configuration
class MongoConfig {
}
