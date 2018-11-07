package com.kessir.weatherreport.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document(collection = "weatherreport")
data class DailyWeather(
        val locationName: String,
        val locationId: Long,
        val maxTemp: Double,
        val minTemp: Double,
        val averageTemp: Double,
        val date: LocalDateTime,
        val status: AlertSatus = AlertSatus.UNPROCESSED,
        val updatedAt: LocalDateTime = LocalDateTime.now(),
        @Id val id: String = UUID.randomUUID().toString()
)

enum class AlertSatus {
    UNPROCESSED, NORMAL, EXTREME_LOW, EXTREME_HIGH
}
