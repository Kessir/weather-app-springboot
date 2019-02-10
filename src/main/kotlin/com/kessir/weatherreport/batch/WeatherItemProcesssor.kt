package com.kessir.weatherreport.batch

import com.kessir.weatherreport.domain.WeatherDataSource
import com.kessir.weatherreport.domain.model.Location
import com.kessir.weatherreport.domain.model.LocationTemps
import com.kessir.weatherreport.services.WeatherClassifier
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor

class WeatherItemProcesssor(private val weatherDataSource: WeatherDataSource,
                            private val weatherClassifier: WeatherClassifier) : ItemProcessor<Location, LocationTemps> {
    private val logger = LoggerFactory.getLogger(WeatherItemProcesssor::class.java)

    override fun process(item: Location): LocationTemps {
        println("Processing single location: ${item.city}");

        val temps = weatherDataSource.getWeatherByLatLong(item.latitude, item.longitude)

        return weatherClassifier.classify(temps, item)
    }
}
