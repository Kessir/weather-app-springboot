package com.kessir.weatherreport

import com.kessir.weatherreport.data.model.DailyWeather

interface WeatherApiClient {
    fun getWeatherByLocationName(location: String): List<DailyWeather>
}
