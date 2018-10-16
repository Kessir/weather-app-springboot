package com.kessir.weatherreport

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WeatherReportApplication

fun main(args: Array<String>) {
    runApplication<WeatherReportApplication>(*args)
}
