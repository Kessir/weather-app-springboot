package com.kessir.weatherreport

import com.kessir.weatherreport.data.AppDataRepository
import com.kessir.weatherreport.data.WeatherRepository
import com.kessir.weatherreport.data.model.AppData
import com.kessir.weatherreport.data.model.Location
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class WeatherReportApplication : CommandLineRunner {

    @Autowired
    lateinit var weatherRepository: WeatherRepository
    @Autowired
    lateinit var appDataRepository: AppDataRepository

    override fun run(vararg args: String?) {

        val locations = listOf(
                Location("Paris", "FR", 48.86, 2.34, -5.0, 35.0),
                Location("Libreville", "GA", 0.39, 9.45, 10.0, 35.0))

        if (appDataRepository.count() <= 0) {
            weatherRepository.deleteAll();

            appDataRepository.save(AppData(
                    id = "appdata",
                    dataFetchIntervalSec = 240,
                    locations = locations
            ))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<WeatherReportApplication>(*args)
}
