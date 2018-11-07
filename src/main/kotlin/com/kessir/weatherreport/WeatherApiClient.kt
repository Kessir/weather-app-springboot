package com.kessir.weatherreport

import com.kessir.weatherreport.data.model.Weather

interface WeatherApiClient {
    fun getWeatherByLocationName(location: String): List<Weather>
}
