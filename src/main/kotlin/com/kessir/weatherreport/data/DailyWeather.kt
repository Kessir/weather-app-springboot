package com.kessir.weatherreport.data

import org.springframework.data.annotation.Id
import java.util.*

data class DailyWeather (
        @Id val id: String,
        val locationName: String,
        val locationId: Long,
        val maxTemp: Int,
        val minTemp: Int,
        val averageTemp: Int,
        val date: Date
)
