package com.kessir.weatherreport.services

import com.kessir.weatherreport.domain.model.AlertStatus
import org.springframework.stereotype.Service


@Service
class WeatherClassificationLogic {
    fun getStatus(minTemp: Double, maxTemp: Double, lowerLimit: Double, upperLimit: Double): AlertStatus =
            if (minTemp < lowerLimit && maxTemp > upperLimit) {
                AlertStatus.EXTREME_LOW_AND_HIGH
            } else if (minTemp < lowerLimit) {
                AlertStatus.EXTREME_LOW
            } else if (maxTemp > upperLimit) {
                AlertStatus.EXTREME_HIGH
            } else {
                AlertStatus.NORMAL
            }
}

