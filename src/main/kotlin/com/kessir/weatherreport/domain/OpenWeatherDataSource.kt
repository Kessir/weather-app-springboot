package com.kessir.weatherreport.domain

import com.kessir.weatherreport.domain.model.UnprocessedTemperature
import com.kessir.weatherreport.domain.model.WeatherApiResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder


@Component
class OpenWeatherDataSource : WeatherDataSource {

    private val restTemplate = RestTemplate()

    @Value("\${openweather.appId}")
    private lateinit var APPID: String

    override fun getWeatherByLatLong(lat: Double, lon: Double): List<UnprocessedTemperature> {
        val builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("APPID", APPID)
                .queryParam("lat", lat)
                .queryParam("lon", lon)

        return getTemps(builder.toUriString())
    }

    private fun getTemps(fullUrl: String): List<UnprocessedTemperature> {
        val response: WeatherApiResponse? = restTemplate.getForObject(fullUrl, WeatherApiResponse::class.java)

        return response?.list?.map {
            UnprocessedTemperature(
                    maxTemp = it.main.temp_max,
                    minTemp = it.main.temp_min,
                    averageTemp = it.main.temp,
                    date = it.dt_txt
            )
        } ?: emptyList()
    }

    companion object {
        private const val BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?units=metric"
    }
}

