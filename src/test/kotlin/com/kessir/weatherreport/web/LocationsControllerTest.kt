package com.kessir.weatherreport.web

import com.kessir.weatherreport.domain.model.Location
import com.kessir.weatherreport.services.AppDataService
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
import java.util.*


@RunWith(SpringRunner::class)
@WebMvcTest(LocationsController::class)
class LocationsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var locationsService: LocationsService

    @MockBean
    private lateinit var temperaturesService: TemperaturesService

    @MockBean
    private lateinit var appDataService: AppDataService


    private val LOCATIONS_LIST = listOf(Location("Lisbon", "PG", 1.0, 1.0, 10.0, 30.0, "ee7e960c-7500-4cad-b86e-cbe35044697e"))

    @Test
    fun `get list of locations`() {
        given(this.locationsService.getAll())
                .willReturn(LOCATIONS_LIST)

        val expectedJson = """[{"city":"Lisbon","countryCode":"PG","latitude":1.0,"longitude":1.0,"minTempLimit":10.0,"maxTempLimit":30.0,"id":"ee7e960c-7500-4cad-b86e-cbe35044697e"}]"""

        this.mockMvc.perform(get("/api/locations").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().json(expectedJson));
    }

    @Test
    fun `return single location when it exists`() {
        val locationId = "random-id"
        given(this.locationsService.findById(locationId))
                .willReturn(Optional.of(LOCATIONS_LIST[0]))

        val expectedJson = """{"city":"Lisbon","countryCode":"PG","latitude":1.0,"longitude":1.0,"minTempLimit":10.0,"maxTempLimit":30.0,"id":"ee7e960c-7500-4cad-b86e-cbe35044697e"}"""

        this.mockMvc.perform(get("/api/locations/$locationId").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().json(
                        expectedJson
                ));
    }

    @Test
    fun `return 404 when location does not exist`() {
        val locationId = "random-id"
        given(this.locationsService.findById(locationId))
                .willReturn(Optional.empty())


        this.mockMvc.perform(get("/api/locations/$locationId").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError)

    }
}
