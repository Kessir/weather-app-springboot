package com.kessir.weatherreport

import com.kessir.weatherreport.domain.LocationsRepository
import com.kessir.weatherreport.domain.WeatherRepository
import com.kessir.weatherreport.domain.model.AlertStatus
import com.kessir.weatherreport.domain.model.Location
import com.kessir.weatherreport.domain.model.LocationTemps
import com.kessir.weatherreport.domain.model.Temperature
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = ["classpath:application-test.properties"])
class ApiIntegrationTest {

    private val LOCATION_1 = Location("Lisbon", "PG", 1.0, 1.0, 10.0, 30.0, "ee7e960c-7500-4cad-b86e-cbe35044697e")

    private val TEMPS_1 = LocationTemps(
            locationId = LOCATION_1.id,
            countryCode = LOCATION_1.countryCode,
            city = LOCATION_1.city,
            temperatures = listOf(Temperature(30.0,21.3, 25.0, LocalDateTime.now(), AlertStatus.EXTREME_HIGH ))
    )

    @Autowired
    lateinit var locationsRepository: LocationsRepository

    @Autowired
    lateinit var weatherRepository: WeatherRepository

    @Before
    fun setUp() {
        locationsRepository.deleteAll()
        weatherRepository.deleteAll()

        locationsRepository.save(LOCATION_1)
        weatherRepository.save(TEMPS_1)
    }


    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    @Throws(Exception::class)
    fun `get list of locations`() {
        val result = restTemplate.getForEntity("/api/locations", String::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result.statusCode)
        assertNotNull(result.body)
    }

    @Test
    @Throws(Exception::class)
    fun `get single location`() {
        val result = restTemplate.getForEntity("/api/locations/${LOCATION_1.id}", Location::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result.statusCode)
        assertNotNull(result.body)
        assertEquals(result.body!!.city, LOCATION_1.city)
    }


    @Test
    @Throws(Exception::class)
    fun `get list of temperatures by locations`() {
        val result = restTemplate.getForEntity("/api/weather", String::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result.statusCode)
        assertNotNull(result.body)
    }

    @Test
    @Throws(Exception::class)
    fun `get temperatures for a single location`() {
        val result = restTemplate.getForEntity("/api/weather/${LOCATION_1.id}", LocationTemps::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result.statusCode)
        assertNotNull(result.body)
        assertNotEquals(result.body!!.temperatures, 0)

    }
}
