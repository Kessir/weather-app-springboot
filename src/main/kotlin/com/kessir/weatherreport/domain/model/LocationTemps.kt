package com.kessir.weatherreport.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "weather")
data class LocationTemps(@Id val locationId: String,
                         val city: String,
                         val countryCode: String,
                         val temperatures: List<Temperature>,
                         val updatedAt: LocalDateTime = LocalDateTime.now())
