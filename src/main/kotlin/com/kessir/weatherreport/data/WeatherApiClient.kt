package com.kessir.weatherreport.data

import com.kessir.weatherreport.data.model.DailyWeather

interface WeatherApiClient {
    fun getWeatherByLocationName(location: String, countryCode: String): List<DailyWeather>
    fun getWeatherByLatLong(lat: Double, lon: Double): List<DailyWeather>
}
