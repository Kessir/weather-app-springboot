package com.kessir.weatherreport.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document(collection = "weatherreport")
data class DailyWeather(
        val locationName: String,
        val locationId: Long,
        val maxTemp: Int,
        val minTemp: Int,
        val averageTemp: Int,
        val date: LocalDateTime,
        val updatedAt: LocalDateTime = LocalDateTime.now(),
        @Id val id: String = UUID.randomUUID().toString()
)
