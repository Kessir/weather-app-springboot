package com.kessir.weatherreport

import com.kessir.weatherreport.data.model.AlertSatus
import com.kessir.weatherreport.data.model.DailyWeather
import org.springframework.stereotype.Component

@Component
class TempClassifier() {
    fun classify(weather: DailyWeather, minTemp: Double, maxTemp: Double): DailyWeather {
        return DailyWeather(
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
}
