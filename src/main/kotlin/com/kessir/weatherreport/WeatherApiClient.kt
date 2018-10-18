package com.kessir.weatherreport

import com.kessir.weatherreport.data.DailyWeather

interface WeatherApiClient {
    fun getWeatherByLocationName(location: String): List<DailyWeather>
    fun test(): String?
}
