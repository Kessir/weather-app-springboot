package com.kessir.weatherreport.domain

import com.kessir.weatherreport.domain.model.Temperature

interface WeatherDataSource {
    fun getWeatherByLocationName(location: String, countryCode: String): List<Temperature>
    fun getWeatherByLatLong(lat: Double, lon: Double): List<Temperature>
}
