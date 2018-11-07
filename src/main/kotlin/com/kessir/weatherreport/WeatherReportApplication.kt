package com.kessir.weatherreport

import com.kessir.weatherreport.data.model.DailyWeather
import com.kessir.weatherreport.data.WeatherRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import java.time.LocalDateTime

@SpringBootApplication
@EnableScheduling
class WeatherReportApplication : CommandLineRunner {

    @Autowired
    lateinit var weatherRepository: WeatherRepository

    override fun run(vararg args: String?) {
        weatherRepository.deleteAll();

//        weatherRepository.save(
//                DailyWeather(
//                        "Paris",
//                        1,
//                        30.0,
//                        30.0,
//                        30.0,
//                        LocalDateTime.now()
//                ))
    }
}

fun main(args: Array<String>) {
    runApplication<WeatherReportApplication>(*args)
}
