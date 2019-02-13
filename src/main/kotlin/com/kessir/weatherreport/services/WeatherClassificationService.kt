package com.kessir.weatherreport.services

import com.kessir.weatherreport.domain.WeatherDataSource
import com.kessir.weatherreport.domain.model.Location
import com.kessir.weatherreport.domain.model.LocationTemps
import com.kessir.weatherreport.domain.model.Temperature
import com.kessir.weatherreport.domain.model.UnprocessedTemperature
import org.springframework.stereotype.Service

@Service
class WeatherClassificationService(private val weatherDataSource: WeatherDataSource,
                                   private val weatherClassificationLogic: WeatherClassificationLogic) {

    fun process(location: Location): LocationTemps{
        val temperatures = weatherDataSource.getWeatherByLatLong(location.latitude, location.longitude)

        val processedTemperatures = temperatures.map {
            getProcessedTemperatures(it, location.minTempLimit, location.maxTempLimit)
        }

        return LocationTemps(
                locationId = location.id,
                city = location.city,
                countryCode = location.countryCode,
                temperatures = processedTemperatures
        )
    }

    private fun getProcessedTemperatures(weather: UnprocessedTemperature, minTemp: Double, maxTemp: Double): Temperature {
        return Temperature(
                maxTemp = weather.maxTemp,
                minTemp = weather.minTemp,
                averageTemp = weather.averageTemp,
                date = weather.date,
                status = weatherClassificationLogic.getStatus(
                        minTemp = weather.minTemp,
                        maxTemp = weather.maxTemp,
                        lowerLimit = minTemp,
                        upperLimit = maxTemp
                )
        )
    }
}
