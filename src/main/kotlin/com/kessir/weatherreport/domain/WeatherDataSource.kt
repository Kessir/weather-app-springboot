package com.kessir.weatherreport.domain

import com.kessir.weatherreport.domain.model.UnprocessedTemperature

interface WeatherDataSource {
    fun getWeatherByLatLong(lat: Double, lon: Double): List<UnprocessedTemperature>
}
