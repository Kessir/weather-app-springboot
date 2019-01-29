package com.kessir.weatherreport.batch

import com.kessir.weatherreport.services.WeatherClassifier
import com.kessir.weatherreport.data.WeatherApiClient
import com.kessir.weatherreport.data.WeatherRepository
import com.kessir.weatherreport.data.model.Location
import com.kessir.weatherreport.data.model.LocationTemps
import com.kessir.weatherreport.services.AppDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


const val DEFAULT_INTERVAL_IN_MS = 600_000L

@Component
class ScheduledTasks(private val apiClient: WeatherApiClient,
                     val weatherClassifier: WeatherClassifier,
                     val appDataService: AppDataService,
                     val weatherRepository: WeatherRepository) {

//         TODO: 1. Load cities and temp limit
//         TODO: 2. Fetch tem for cities
//         TODO: 3. classify
//         TODO: 4. write lo logs and/or db


    @Scheduled(fixedDelayString = "\${fetch.rate:$DEFAULT_INTERVAL_IN_MS}")
    fun fetchWeatherData() {

        requestWeatherData(appDataService.getLocations())
    }

    private fun requestWeatherData(locations: List<Location>) {
        locations.forEach { location ->
            val temps = apiClient.getWeatherByLatLong(location.lat, location.lon)

            val processedTemperatures = temps.map {
                weatherClassifier.classify(it, location.minTempLimit, location.maxTempLimit)
            }

            val locationTemps = LocationTemps(
                    locationId = location.id,
                    city = location.city,
                    countryCode = location.countryCode,
                    temps = processedTemperatures
            )

            weatherRepository.save(locationTemps)
        }
    }
}
