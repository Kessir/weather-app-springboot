package com.kessir.weatherreport

import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate


const val DEFAULT_INTERVAL_IN_MS = 60_000L

@Component
class ScheduledTasks(private val apiClient: WeatherApiClient) {
    @Value("\${spring.application.name}")
    private val name: String? = null


    @Scheduled(fixedDelayString = "\${some.rate:$DEFAULT_INTERVAL_IN_MS}")
    fun printTime(){
        val result = apiClient.test()
            System.out.println("======== HELLO =========")
            System.out.println(result)

    }
}
