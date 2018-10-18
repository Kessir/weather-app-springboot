package com.kessir.weatherreport

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class WeatherReportApplication

fun main(args: Array<String>) {
    runApplication<WeatherReportApplication>(*args)
}
