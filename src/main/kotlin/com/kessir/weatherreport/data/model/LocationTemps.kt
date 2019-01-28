package com.kessir.weatherreport.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "weather")
data class LocationTemps(@Id val locationId: String,
                         val city: String,
                         val countryCode: String,
                         val temps: List<DailyWeather>,
                         val updatedAt: LocalDateTime = LocalDateTime.now())
