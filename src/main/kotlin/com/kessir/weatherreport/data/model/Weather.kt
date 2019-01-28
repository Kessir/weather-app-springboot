package com.kessir.weatherreport.data.model

import java.time.LocalDateTime

data class DailyWeather(
        val maxTemp: Double,
        val minTemp: Double,
        val averageTemp: Double,
        val date: LocalDateTime,
        val status: AlertSatus = AlertSatus.UNPROCESSED)

enum class AlertSatus {
    UNPROCESSED, NORMAL, EXTREME_LOW, EXTREME_HIGH
}
