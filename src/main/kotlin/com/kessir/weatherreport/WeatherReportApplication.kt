package com.kessir.weatherreport

import com.kessir.weatherreport.services.AppDataService
import com.kessir.weatherreport.services.LocationsService
import com.kessir.weatherreport.services.TemperaturesService
import com.kessir.weatherreport.utils.CsvParser
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
class WeatherReportApplication(val locationsService: LocationsService, val temperaturesService: TemperaturesService)
    : CommandLineRunner {

    override fun run(vararg args: String?) {
        if (locationsService.getAll().isEmpty()) {
            initialDataSetUp()
        }
    }

    private fun initialDataSetUp() {
        println("Initial data set up")

        temperaturesService.deleteAll()

        val locations = CsvParser().loadLocationsList("locations.csv")

        if (locations.isEmpty()) {
            println("Could not load any location. Exiting the app")
            throw RuntimeException("Could not load any location.")

        }
        locationsService.saveAll(locations)

        launchDataFetchJobs()
    }

    private fun launchDataFetchJobs() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

fun main(args: Array<String>) {
    runApplication<WeatherReportApplication>(*args)
}
