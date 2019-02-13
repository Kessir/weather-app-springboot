package com.kessir.weatherreport.services

import com.kessir.weatherreport.domain.WeatherDataSource
import com.kessir.weatherreport.domain.model.AlertStatus
import com.kessir.weatherreport.domain.model.Location
import com.kessir.weatherreport.domain.model.UnprocessedTemperature
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import  org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
class WeatherClassificationServiceTest {

    private lateinit var weatherClassificationService: WeatherClassificationService

    @MockBean
    lateinit var weatherDataSource: WeatherDataSource

    private val temperatures = listOf(UnprocessedTemperature(35.0, 21.3, 25.0, LocalDateTime.of(2019, 2, 5, 10, 30)))
    private val LOCATION = Location("Lisbon", "PG", 1.0, 1.0, 10.0, 30.0, "ee7e960c-7500-4cad-b86e-cbe35044697e")

    @Before
    fun setup() {
        weatherClassificationService = WeatherClassificationService(weatherDataSource, WeatherClassificationLogic())
    }

    @Test
    fun classify() {
        given(this.weatherDataSource.getWeatherByLatLong(LOCATION.latitude, LOCATION.longitude))
                .willReturn(temperatures)

        val result = weatherClassificationService.process(LOCATION)
        assertEquals(temperatures.size, result.temperatures.size)
        assertEquals(AlertStatus.EXTREME_HIGH, result.temperatures[0].status)
    }
}
