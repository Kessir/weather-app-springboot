package com.kessir.weatherreport.data.model

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

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

