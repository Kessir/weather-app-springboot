package com.kessir.weatherreport

import com.kessir.weatherreport.data.WeatherApiClient
import com.kessir.weatherreport.data.model.DailyWeather
import com.kessir.weatherreport.data.model.WeatherApiResponse
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

const val URL = "http://api.openweathermap.org/data/2.5/forecast?q=paris,FR&APPID=eecc9f86036f8ccaf556ddd22ddbce51&units=metric"

@Component
class OpenWeatherClient : WeatherApiClient {
    override fun getWeatherByLatLong(lat: Double, lon: Double): List<DailyWeather> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val restTemplate = RestTemplate()

    override fun getWeatherByLocationName(location: String, countryCode: String): List<DailyWeather> {

        val response: WeatherApiResponse? = restTemplate.getForObject(URL, WeatherApiResponse::class.java)

        return response?.list?.map {
            DailyWeather(
                    maxTemp = it.main.temp_max,
                    minTemp = it.main.temp_min,
                    averageTemp = it.main.temp,
                    date = it.dt_txt
            )
        } ?: emptyList()
    }
}

