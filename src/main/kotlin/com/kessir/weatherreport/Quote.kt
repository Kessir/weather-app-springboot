package com.kessir.weatherreport

import java.util.*

data class Quote(val type: String)
data class WeatherApiResponse(val list: List<SingleReport>, val city: City)

data class City(val id: Long, val name:String, val country: String)

data class SingleReport(val main: Temps, val dt_txt: Date)

data class Temps(
        val temp: Int,
        val temp_min: Int,
        val temp_max: Int
)
