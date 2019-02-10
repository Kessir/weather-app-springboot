package com.kessir.weatherreport.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "locations")
data class Location(
        val city: String,
        val countryCode: String,
        val latitude: Double,
        val longitude: Double,
        val minTempLimit: Double,
        val maxTempLimit: Double,
        @Id val id: String = UUID.randomUUID().toString(),
        val isMonitored: Boolean = true)
