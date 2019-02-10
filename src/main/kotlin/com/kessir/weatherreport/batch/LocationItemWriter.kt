package com.kessir.weatherreport.batch

import com.kessir.weatherreport.domain.WeatherRepository
import com.kessir.weatherreport.domain.model.LocationTemps
import org.springframework.batch.item.ItemWriter

class LocationItemWriter(private val weatherRepository: WeatherRepository) : ItemWriter<LocationTemps> {
    override fun write(items: MutableList<out LocationTemps>) {
        weatherRepository.saveAll(items)
    }

}
