package com.kessir.weatherreport.data.model

import org.springframework.data.annotation.Id
import java.util.*

data class Location(
        val city: String,
        val countryCode: String,
        val lat: Double,
        val lon: Double,
        val minTempLimit: Double,
        val maxTempLimit: Double,
        @Id val id: String = UUID.randomUUID().toString())
