package com.kessir.weatherreport.data.model

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "appData")
data class AppData(val id: String, val dataFetchIntervalSec: Int, val locations: List<Location>)

