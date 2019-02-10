package com.kessir.weatherreport.domain.model

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "appData")
data class AppData(val id: String, val dataFetchIntervalSec: Int)

