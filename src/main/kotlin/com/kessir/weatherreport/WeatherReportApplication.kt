package com.kessir.weatherreport

import com.kessir.weatherreport.data.WeatherRepository
import com.kessir.weatherreport.data.model.Location
import com.kessir.weatherreport.services.AppDataService
import com.kessir.weatherreport.utils.CsvReader
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class WeatherReportApplication(val weatherRepository: WeatherRepository, val appDataService: AppDataService)
    : CommandLineRunner {
    var logger = LoggerFactory.getLogger(WeatherReportApplication::class.java)

    override fun run(vararg args: String?) {
        if (!appDataService.isAppDataSetUp()) {
            initialDataSetUp()
        }
    }

    private fun initialDataSetUp() {
        logger.debug("Initial data set up")

        weatherRepository.deleteAll()

        val locations = CsvReader().loadLocationsList("locations.csv")

        if (locations.isEmpty()) {
            logger.error("Could not load any location. Exiting the app")
            return
        }

        appDataService.updateData(dataFetchIntervalSec = 300, locations = locations)

        launchDataFetchJobs(locations)
    }

    private fun launchDataFetchJobs(locations: List<Location>) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

fun main(args: Array<String>) {
    runApplication<WeatherReportApplication>(*args)
}
