package com.kessir.weatherreport.web

import com.kessir.weatherreport.domain.model.AlertStatus
import com.kessir.weatherreport.domain.model.Location
import com.kessir.weatherreport.domain.model.LocationTemps
import com.kessir.weatherreport.domain.model.Temperature
import com.kessir.weatherreport.services.LocationsService
import com.kessir.weatherreport.services.TemperaturesService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import java.time.LocalDateTime


@RunWith(SpringRunner::class)
@WebMvcTest(WeatherController::class)
class WeatherControllerTest {
    private val LOCATION_1 = Location("Lisbon", "PG", 1.0, 1.0, 10.0, 30.0, "ee7e960c-7500-4cad-b86e-cbe35044697e")

    private val TEMPS_1 = LocationTemps(
            locationId = LOCATION_1.id,
            countryCode = LOCATION_1.countryCode,
            city = LOCATION_1.city,
            temperatures = listOf(Temperature(30.0,21.3, 25.0, LocalDateTime.of(2019,2,5,10,30), AlertStatus.EXTREME_HIGH ))
    )
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var locationsService: LocationsService

    @MockBean
    private lateinit var temperaturesService: TemperaturesService

    @Test
    fun `get temperatures for all locations`() {
        given(this.temperaturesService.getAll())
                .willReturn(listOf(TEMPS_1))

        val expectedJson = """[{"locationId":"ee7e960c-7500-4cad-b86e-cbe35044697e","city":"Lisbon","countryCode":"PG","temperatures":[{"maxTemp":30.0,"minTemp":21.3,"averageTemp":25.0,"date":"2019-02-05T10:30:00","status":"EXTREME_HIGH"}]}]"""

        this.mockMvc.perform(get("/api/weather")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().json(expectedJson));
    }

    @Test
    fun `get temperature for single location`() {
        given(this.temperaturesService.findById(LOCATION_1.id))
                .willReturn(TEMPS_1)

        val expectedJson = """{"locationId":"ee7e960c-7500-4cad-b86e-cbe35044697e","city":"Lisbon","countryCode":"PG","temperatures":[{"maxTemp":30.0,"minTemp":21.3,"averageTemp":25.0,"date":"2019-02-05T10:30:00","status":"EXTREME_HIGH"}]}
"""

        this.mockMvc.perform(get("/api/weather/${LOCATION_1.id}").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().json(expectedJson));
    }

    @Test
    fun `return 404 when location does not exist`() {
        val locationId = "random-id"
        given(this.locationsService.findById(locationId))
                .willReturn(null)


        this.mockMvc.perform(get("/api/weather/$locationId")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError)

    }
}
