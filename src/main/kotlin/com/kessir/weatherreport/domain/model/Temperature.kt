package com.kessir.weatherreport.domain.model

import java.time.LocalDateTime

data class Temperature(
        val maxTemp: Double,
        val minTemp: Double,
        val averageTemp: Double,
        val date: LocalDateTime,
        val status: AlertStatus)

