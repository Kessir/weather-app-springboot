package com.kessir.weatherreport.data

import com.kessir.weatherreport.data.model.DailyWeather
import com.kessir.weatherreport.data.model.WeatherApiResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder



@Component
class OpenWeatherClient : WeatherApiClient {

    private val restTemplate = RestTemplate()

    @Value("\${openweather.appId}")
    private lateinit var APPID: String

    override fun getWeatherByLatLong(lat: Double, lon: Double): List<DailyWeather> {
        val builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("APPID", APPID)
                .queryParam("lat", lat)
                .queryParam("lon", lon)

        return getTemps(builder.toUriString())
    }

    override fun getWeatherByLocationName(location: String, countryCode: String): List<DailyWeather> {
        val builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("APPID", APPID)
                .queryParam("q", "$location, $countryCode")

        return getTemps(builder.toUriString())
    }

    private fun getTemps(fullUrl: String): List<DailyWeather> {
        val response: WeatherApiResponse? = restTemplate.getForObject(fullUrl, WeatherApiResponse::class.java)

        return response?.list?.map {
            DailyWeather(
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

