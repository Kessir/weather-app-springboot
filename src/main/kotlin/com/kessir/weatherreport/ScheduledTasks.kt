package com.kessir.weatherreport

import com.kessir.weatherreport.data.WeatherRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


const val DEFAULT_INTERVAL_IN_MS = 600_000L

@Component
class ScheduledTasks(private val apiClient: WeatherApiClient, val tempClassifier: TempClassifier) {
    @Value("\${spring.application.name}")
    private val name: String? = null


    @Autowired
    lateinit var weatherRepository: WeatherRepository

    @Scheduled(fixedDelayString = "\${some.rate:$DEFAULT_INTERVAL_IN_MS}")
    fun fetchWeather() {

//         TODO: 1. Load cities and temp limit
//         TODO: 2. Fetch tem for cities
//         TODO: 3. classify
//         TODO: 4. write lo logs and/or db


        val temps = apiClient.getWeatherByLocationName("Paris")

        val result = temps.map {
            tempClassifier.classify(it)
        }


        weatherRepository.saveAll(result)

    }
}
