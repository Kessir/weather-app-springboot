package com.kessir.weatherreport.domain.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class WeatherApiResponse(val list: List<SingleReport>, val city: City)

data class City(val id: Long, val name: String, val country: String)

data class SingleReport(
        val main: Temps,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        val dt_txt: LocalDateTime
)

data class Temps(
        val temp: Double,
        val temp_min: Double,
        val temp_max: Double
)

