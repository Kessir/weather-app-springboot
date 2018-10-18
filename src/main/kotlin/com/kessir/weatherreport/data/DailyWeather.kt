package com.kessir.weatherreport.data

import java.util.*

data class DailyWeather (
        val locationName: String,
        val locationId: Long,
        val maxTemp: Int,
        val minTemp: Int,
        val averageTemp: Int,
        val date: Date
)
