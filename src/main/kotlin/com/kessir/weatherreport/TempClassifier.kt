package com.kessir.weatherreport

import com.kessir.weatherreport.data.model.AlertSatus
import com.kessir.weatherreport.data.model.DailyWeather
import org.springframework.stereotype.Component

@Component
class TempClassifier() {
    val UPPER_LIMIT = 30.0
    val LOWER_LIMIT = -2.0
    fun classify(weather: DailyWeather): DailyWeather {
        return DailyWeather(
                locationName = weather.locationName,
                locationId = weather.locationId,
                maxTemp = weather.maxTemp,
                minTemp = weather.minTemp,
                averageTemp = weather.averageTemp,
                date = weather.date,
                status = getStatus(
                        minTemp = weather.minTemp,
                        maxTemp = weather.maxTemp,
                        lowerLimit = LOWER_LIMIT,
                        upperLimit = UPPER_LIMIT
                )
        )
    }

    fun getStatus(minTemp: Double, maxTemp: Double, lowerLimit: Double, upperLimit: Double): AlertSatus {
        if (maxTemp > upperLimit) return AlertSatus.EXTREME_HIGH
        if (minTemp < lowerLimit) return AlertSatus.EXTREME_LOW
        return AlertSatus.NORMAL
    }
}
