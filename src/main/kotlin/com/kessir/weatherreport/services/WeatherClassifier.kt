package com.kessir.weatherreport.services

import com.kessir.weatherreport.domain.model.*
import org.springframework.stereotype.Service

@Service
class WeatherClassifier() {
    private fun classify(weather: Temperature, minTemp: Double, maxTemp: Double): Temperature {
        return Temperature(
                maxTemp = weather.maxTemp,
                minTemp = weather.minTemp,
                averageTemp = weather.averageTemp,
                date = weather.date,
                status = getStatus(
                        minTemp = weather.minTemp,
                        maxTemp = weather.maxTemp,
                        lowerLimit = minTemp,
                        upperLimit = maxTemp
                )
        )
    }

    private fun getStatus(minTemp: Double, maxTemp: Double, lowerLimit: Double, upperLimit: Double): AlertSatus {
        if (maxTemp > upperLimit) return AlertSatus.EXTREME_HIGH
        if (minTemp < lowerLimit) return AlertSatus.EXTREME_LOW
        return AlertSatus.NORMAL
    }

    fun classify(temperatures: List<Temperature>, location: Location): LocationTemps{
        val processedTemperatures = temperatures.map {
            classify(it, location.minTempLimit, location.maxTempLimit)
        }

        return LocationTemps(
                locationId = location.id,
                city = location.city,
                countryCode = location.countryCode,
                temperatures = processedTemperatures
        )
    }
}
