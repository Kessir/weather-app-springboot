package com.kessir.weatherreport.domain.model

import java.time.LocalDateTime

data class Temperature(
        val maxTemp: Double,
        val minTemp: Double,
        val averageTemp: Double,
        val date: LocalDateTime,
        val status: AlertSatus = AlertSatus.UNPROCESSED)

enum class AlertSatus {
    UNPROCESSED, NORMAL, EXTREME_LOW, EXTREME_HIGH
}
