package com.kessir.weatherreport

import com.kessir.weatherreport.data.WeatherRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate


const val DEFAULT_INTERVAL_IN_MS = 600_000L

@Component
class ScheduledTasks(private val apiClient: WeatherApiClient) {
    @Value("\${spring.application.name}")
    private val name: String? = null

    @Autowired
    lateinit var weatherRepository: WeatherRepository

    @Scheduled(fixedDelayString = "\${some.rate:$DEFAULT_INTERVAL_IN_MS}")
    fun printTime() {
        val result = apiClient.getWeatherByLocationName("Paris")
        System.out.println(result)
        weatherRepository.saveAll(result)

    }
}
