package com.kessir.weatherreport.domain.model

import java.time.LocalDateTime

data class UnprocessedTemperature(
        val maxTemp: Double,
        val minTemp: Double,
        val averageTemp: Double,
        val date: LocalDateTime)
