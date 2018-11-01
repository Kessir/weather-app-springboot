package com.kessir.weatherreport.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class DailyWeather(
        val locationName: String,
        val locationId: Long,
        val maxTemp: Int,
        val minTemp: Int,
        val averageTemp: Int,
        val date: Date = Date(),
        @Id val id: String = ""
)
