package com.kessir.weatherreport

import com.kessir.weatherreport.data.model.DailyWeather
import com.kessir.weatherreport.data.model.Weather
import com.kessir.weatherreport.data.model.WeatherApiResponse
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

const val URL = "http://api.openweathermap.org/data/2.5/forecast?q=paris,FR&APPID=eecc9f86036f8ccaf556ddd22ddbce51&units=metric"

@Component
class OpenWeatherClient : WeatherApiClient {
    private val restTemplate = RestTemplate()

    override fun getWeatherByLocationName(location: String): List<Weather> {

        val response: WeatherApiResponse? = restTemplate.getForObject(URL, WeatherApiResponse::class.java)

        return response?.list?.map {
            Weather(
                    locationName = response.city.name,
                    locationId = response.city.id,
                    maxTemp = it.main.temp_max,
                    minTemp = it.main.temp_min,
                    averageTemp = it.main.temp,
                    date = it.dt_txt
            )
        } ?: emptyList()
    }
}

